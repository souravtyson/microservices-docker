package com.sourav.userservice.service.impl;

import com.sourav.userservice.entity.UserDetails;
import com.sourav.userservice.model.Product;
import com.sourav.userservice.repository.UserRepository;
import com.sourav.userservice.service.ProductServiceFeignClient;
import com.sourav.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final ProductServiceFeignClient productServiceFeignClient;

    public UserServiceImpl(ProductServiceFeignClient productServiceFeignClient) {
        this.productServiceFeignClient = productServiceFeignClient;
    }


    @Override
    public UserDetails getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserDetails addUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public List<UserDetails> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByUserId(Long id) {
        return productServiceFeignClient.getProductsByUserId(id);
    }
}
