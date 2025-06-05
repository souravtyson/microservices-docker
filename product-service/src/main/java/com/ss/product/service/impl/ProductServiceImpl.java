package com.ss.product.service.impl;

import com.ss.product.entity.Product;
import com.ss.product.repository.ProductRepo;
import com.ss.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Sourav Suman - 05-06-2025
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> byId = productRepo.findById(productId);
        return byId.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        return productRepo.findProductByUserId(userId);
    }
}
