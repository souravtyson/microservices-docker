package com.sourav.userservice.service;

import com.sourav.userservice.entity.UserDetails;
import com.sourav.userservice.model.Product;

import java.util.List;

public interface UserService {
    UserDetails getUserByUserId(Long userId);
    UserDetails addUser(UserDetails userDetails);
    List<UserDetails> fetchAllUsers();
    List<Product> getAllProductsByUserId(Long id);
}
