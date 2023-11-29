package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Photo;
import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.payload.dto.ProductDTO;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import com.example.IPRWC.Backend.util.ImageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PhotoService photoService;

    public ResponseEntity<?> getAllProducts() {
        String apiUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        if (!productRepository.findAll().isEmpty()) {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Product product : products) {
                ProductDTO convertedProduct = ProductDTO.builder()
                        .id(product.getId())
                        .product_name(product.getProduct_name())
                        .age_rating(product.getAge_rating())
                        .price(product.getPrice())
                        .genre(product.getGenre())
                        .description(product.getDescription())
                        .image(apiUrl + "/api/photos/" + product.image.getName()).build();

                productDTOList.add(convertedProduct);
            }
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(productDTOList);
        } else {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No products found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getProduct(Long id) {
        String apiUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        if (productRepository.findById(id).isPresent()) {
            Product product = this.productRepository.findById(id).get();

            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No product found with id: " + id, HttpStatus.NOT_FOUND.name()), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> saveProduct(ProductDTO productDTO, MultipartFile image) throws IOException {

        Product product = objectMapper.convertValue(productDTO, Product.class);
        Photo photo = Photo.builder()
                .name(image.getOriginalFilename())
                .type(image.getContentType())
                .data(ImageUtils.compressImage(image.getBytes()))
                .build();
        Product productWithImage = product.withImage(photo);

        this.productRepository.save(productWithImage);
        return new ResponseEntity<>(productWithImage, HttpStatus.OK);
    }

    public ResponseEntity<?> editProduct(Long id, ProductDTO product, MultipartFile image) {
        if (productRepository.findById(id).isPresent()) {
            Product _product = productRepository.findById(id).get();
            _product.setPrice(product.getPrice());
            _product.setDescription(product.getDescription());
            _product.setProduct_name(product.getProduct_name());
            productRepository.save(_product);
        }
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Product with id: " + id + "deleted"));
        }
        return new ResponseEntity(new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Product with id: " + id + "doesn't exist!",
                HttpStatus.UNPROCESSABLE_ENTITY.name()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
