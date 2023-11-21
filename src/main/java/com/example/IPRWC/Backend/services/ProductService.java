package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.ProductResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PhotoService photoService;

    public ResponseEntity<?> getAllProducts() {
        if (!productRepository.findAll().isEmpty()) {
            List<Product> products = productRepository.findAll();
            ProductResponse productResponse = ProductResponse.builder()
                    .message("OK")
                    .products(products)
                    .build();

            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No products found").getHttpStatus());
        }
    }

    public ResponseEntity<?> getProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            Product product = this.productRepository.findById(id).get();
            ProductResponse productResponse = ProductResponse.builder().product(product).message("OK").build();

            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No product found with id: " + id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> saveProduct(Product product, MultipartFile image) throws IOException {
        if (product == null){

        }
        this.photoService.store(image);
        this.productRepository.save(product);
        ProductResponse productResponse = ProductResponse.builder().product(product).message("OK").build();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public void editProduct(Long id, Product product) {
        if (productRepository.findById(id).isPresent()) {
            Product _product = productRepository.findById(id).get();
            _product.setPrice(product.getPrice());
            _product.setProduct_rating(product.getProduct_rating());
            _product.setDescription(product.getDescription());
            _product.setProduct_name(product.getProduct_name());
            productRepository.save(_product);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
