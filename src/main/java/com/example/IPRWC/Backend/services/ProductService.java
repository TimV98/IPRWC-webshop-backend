package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Photo;
import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.payload.dto.PhotoDTO;
import com.example.IPRWC.Backend.payload.dto.ProductDTO;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ProductRepository;
import com.example.IPRWC.Backend.util.ImageUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpg", "image/jpeg");


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
                        .image(PhotoDTO.builder()
                                .name(product.image.getName())
                                .type(product.image.getType())
                                .image_url(apiUrl + "/api/photos/" + product.image.getName())
                                .build()).build();


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

    public ResponseEntity<?> saveProduct(String productDTO, MultipartFile image) throws IOException {
        if (!contentTypes.contains(image.getContentType())) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "File is not an image", HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);
        }
        ProductDTO productDTOObj = objectMapper.readValue(productDTO, ProductDTO.class);
        Product product = objectMapper.convertValue(productDTOObj, Product.class);
        Photo photo = Photo.builder()
                .name(image.getOriginalFilename())
                .type(image.getContentType())
                .data(ImageUtils.compressImage(image.getBytes()))
                .build();
        Product productWithImage = product.withImage(photo);

        this.productRepository.save(productWithImage);
        return new ResponseEntity<>(productWithImage, HttpStatus.OK);
    }

    public ResponseEntity<?> editProduct(Long id, String productDTO, MultipartFile image) throws IOException {
        if (productRepository.findById(id).isPresent()) {
            if (!image.getContentType().equals(MediaType.IMAGE_JPEG_VALUE) || !image.getContentType().equals(MediaType.IMAGE_PNG_VALUE)) {
                new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "File is not an image", HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);
            }
            long photoId = productRepository.findById(id).get().image.getId();
            Photo dbPhoto = photoService.getFileInformation(photoId)
                    .withName(image.getOriginalFilename())
                    .withType(image.getContentType())
                    .withData(ImageUtils.compressImage(image.getBytes()));

            ProductDTO productDTOObj = objectMapper.readValue(productDTO, ProductDTO.class);
            Product product = objectMapper.convertValue(productDTOObj, Product.class);

            Product dbProduct = productRepository.findById(id).get()
                    .withProduct_name(product.getProduct_name())
                    .withGenre(product.getGenre())
                    .withPrice(product.getPrice())
                    .withDescription(product.getDescription())
                    .withAge_rating(product.getAge_rating())
                    .withImage(dbPhoto);

            productRepository.save(dbProduct);
            return ResponseEntity.ok("Product with id: " + id + " edited");
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Product with id: " + id + " was not found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Product with id: " + id + " deleted"));
        }
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Product with id: " + id + " doesn't exist!",
                HttpStatus.UNPROCESSABLE_ENTITY.name()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
