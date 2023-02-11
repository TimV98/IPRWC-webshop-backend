package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Product;
import org.springframework.web.bind.annotation.*;
import com.example.IPRWC.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/edit/{id}")
    public Product putProduct(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setPrice(product.getPrice());
            _product.setProduct_rating(product.getProduct_rating());
            _product.setDescription(product.getDescription());
            _product.setProduct_name(product.getProduct_name());
            return productRepository.save(_product);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}