package com.sourav.userservice.service;

import com.sourav.userservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {

    @GetMapping(value = "/product/user/{userId}")
    List<Product> getProductsByUserId(@PathVariable("userId") Long userId);
}
