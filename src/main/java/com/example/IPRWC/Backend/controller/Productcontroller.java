package com.example.IPRWC.Backend.controller;

import com.example.IPRWC.Backend.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.IPRWC.Backend.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class Productcontroller {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping("/products/add")
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
//
//    @PutMapping("/products/{id}")
//    public Product putProduct(@RequestBody Product product, @PathVariable Long id){
//        productRepository.findById(id);
//    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}