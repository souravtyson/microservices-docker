package com.sourav.product.repository;

import com.sourav.product.entity.Product;
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
