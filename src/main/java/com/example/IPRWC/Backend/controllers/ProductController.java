package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.payload.dto.ProductDTO;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import com.example.IPRWC.Backend.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://93.119.4.217", "http://s1108697.nl"}, allowCredentials = "true")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveProduct(@Valid @RequestParam("product") String product, @RequestParam("image") MultipartFile image) throws IOException {
        return this.productService.saveProduct(product, image);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> putProduct(@Valid @RequestParam("product") String product, @RequestParam("image") MultipartFile image, @PathVariable Long id) throws IOException {
        return this.productService.editProduct(id, product, image);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return this.productService.deleteProduct(id);
    }


}