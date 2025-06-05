package com.sourav.product.service;

import com.sourav.product.entity.Product;

import java.util.List;

/**
 * @author Sourav Suman - 05-06-2025
 */
public interface ProductService {
    Product addProduct(Product product);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByUserId(Long userId);
}
