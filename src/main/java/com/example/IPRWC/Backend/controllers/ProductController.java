package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {
        if (!productRepository.findAll().isEmpty())
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        if (productRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("Product with given id not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product added!"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> putProduct(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setPrice(product.getPrice());
            _product.setProduct_rating(product.getProduct_rating());
            _product.setDescription(product.getDescription());
            _product.setProduct_name(product.getProduct_name());
            productRepository.save(_product);
            return ResponseEntity.ok(new MessageResponse("Product with id: " + _product.getId() + " has been edited"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Product not edited"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Product deleted"));
        }
        return ResponseEntity.unprocessableEntity().body("Product doesn't exist!");
    }
}