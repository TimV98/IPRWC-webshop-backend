package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.ShoppingCart;
import com.example.IPRWC.Backend.entities.ShoppingCartItem;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.repository.ShoppingCartItemRepository;
import com.example.IPRWC.Backend.repository.ShoppingCartRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping("/get/{id}")
    public Optional<ShoppingCart> getCartById(@PathVariable Long id) {
        return shoppingCartRepository.findById(id);

    }

    @GetMapping("/get")
    public Optional<ShoppingCart> getCartByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        return shoppingCartRepository.findByUserEmail(email);

    }

    @PostMapping("/add")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart shoppingCart, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Optional<ShoppingCart> _shoppingCart = shoppingCartRepository.findByUserEmail(authentication.getName());
        if (user.isEmpty()) {
            shoppingCart.setUser(null);
            return shoppingCartRepository.save(shoppingCart);
        }
        if (_shoppingCart.isPresent()) {
            ShoppingCart shoppingCartData = _shoppingCart.get();
            shoppingCartData.getProducts();
            shoppingCartData.setProducts(shoppingCart.getProducts());
            return shoppingCartRepository.save(shoppingCartData);
        }
        shoppingCart.setUser(user.get());
        return shoppingCartRepository.save(shoppingCart);
    }

    @GetMapping("/getItem/{id}")
    public Optional<ShoppingCartItem> getCartByUserw(@PathVariable Long id) {
        return shoppingCartItemRepository.findById(id);
    }


    @PutMapping("/edit/{id}")
    public ShoppingCart editQuantity(@RequestBody ShoppingCart shoppingCart, @PathVariable Long id) {
        Optional<ShoppingCart> shoppingCartData = shoppingCartRepository.findById(id);
        if (shoppingCartData.isPresent()) {
            ShoppingCart _shoppingCart = shoppingCartData.get();
            for (int i = 0; i < _shoppingCart.getProducts().size(); i++) {
                _shoppingCart.getProducts().get(i).setQuantity(shoppingCart.getProducts().get(i).getQuantity());
            }
            return shoppingCartRepository.save(_shoppingCart);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCart> _shoppingcart = shoppingCartRepository.findById(id);
        ShoppingCart shoppingCart = _shoppingcart.get();
        shoppingCart.setUser(null);
        shoppingCartRepository.save(shoppingCart);
        shoppingCartRepository.deleteById(id);
    }

    @DeleteMapping("/delete/item/{id}")
    public void deleteShoppingCartItem(@PathVariable Long id) {
        shoppingCartItemRepository.deleteById(id);
    }
}
