package com.sourav.userservice.controller;

import com.sourav.userservice.entity.UserDetails;
import com.sourav.userservice.model.Product;
import com.sourav.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetails> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByUserId(id));
    }

    @GetMapping(value = "/{id}/products")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAllProductsByUserId(id));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> addUser(@RequestBody UserDetails userDetails) {
        return ResponseEntity.ok(userService.addUser(userDetails));
    }

}
