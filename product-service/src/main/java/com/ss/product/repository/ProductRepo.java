package com.ss.product.repository;

import com.ss.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sourav Suman - 05-06-2025
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findProductByUserId(Long userId);
}
