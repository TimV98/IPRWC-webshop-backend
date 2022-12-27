package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.entities.ShoppingCart;
import com.example.IPRWC.Backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @GetMapping("/getAll")
    public List<ShoppingCart> getShoppingCartItems() {
        return shoppingCartRepository.findAll();
    }

}
