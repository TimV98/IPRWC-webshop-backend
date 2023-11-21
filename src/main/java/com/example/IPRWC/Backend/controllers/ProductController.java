package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import com.example.IPRWC.Backend.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
    public ResponseEntity<?> saveProduct(@Valid @RequestPart("product") Product product, @RequestPart("image") MultipartFile image) throws IOException {
        return this.productService.saveProduct(product, image);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> putProduct(@RequestBody Product product, @PathVariable Long id) {
        this.productService.editProduct(id, product);
        return ResponseEntity.ok(new MessageResponse("Product with id: " + product.getId() + " has been edited"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepository.findById(id).isPresent()) {
            this.productService.deleteProduct(id);
            return ResponseEntity.ok().body(new MessageResponse("Product deleted"));
        }
        return ResponseEntity.unprocessableEntity().body("Product doesn't exist!");
    }
}