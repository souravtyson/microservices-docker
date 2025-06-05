package com.sourav.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sourav Suman - 05-06-2025
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Long productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private Long userId;
}
