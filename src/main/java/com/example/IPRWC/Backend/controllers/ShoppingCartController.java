package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Product;
import com.example.IPRWC.Backend.entities.ShoppingCart;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/add")
    public ShoppingCart addItem(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @PutMapping("/edit/{id}")
    public ShoppingCart editQuantity(@RequestBody ShoppingCart shoppingCart, @PathVariable Long id) {
        Optional<ShoppingCart> shoppingCartItem = shoppingCartRepository.findById(id);
        if (shoppingCartItem.isPresent()) {
            ShoppingCart _shoppingCart = shoppingCartItem.get();
            _shoppingCart.setQuantity(shoppingCart.getQuantity());
            return shoppingCartRepository.save(_shoppingCart);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShoppinCartItem(@PathVariable Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
