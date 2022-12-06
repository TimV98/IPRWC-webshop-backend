package com.example.IPRWC.Backend.controller;

import com.example.IPRWC.Backend.entities.Product;
import org.springframework.web.bind.annotation.*;
import com.example.IPRWC.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

   @PutMapping("/products/{id}")
   public Product putProduct(@RequestBody Product product, @PathVariable Long id){
       Optional<Product> productData = productRepository.findById(id);
       if(productData.isPresent()){
        Product _product = productData.get(); 
        _product.setPrice(product.getPrice());
        _product.setProduct_size(product.getProduct_size());
        _product.setDescription(product.getDescription());
        _product.setImage_url(product.getImage_url());
        _product.setProduct_name(product.getProduct_name());
        return productRepository.save(_product);
    }else{
        return null;
    }

       
   }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}