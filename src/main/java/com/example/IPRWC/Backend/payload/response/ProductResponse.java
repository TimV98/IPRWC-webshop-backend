package com.example.IPRWC.Backend.payload.response;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Product product;
    private List<Product> products;
    private String message;

}
