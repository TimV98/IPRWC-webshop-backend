package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.ShoppingCart;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.repository.ShoppingCartRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    ShoppingCartItemRepository shoppingCartItemRepository;


    @GetMapping("/get")
    public Optional<ShoppingCart> getCartByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        System.out.println("email: "+ email);

        return shoppingCartRepository.findByUserEmail(email);

    }

    @PostMapping("/add")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart shoppingCart, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        shoppingCart.setUser(user.get());
        return shoppingCartRepository.save(shoppingCart);
    }

    @PutMapping("/edit/{id}")
    public ShoppingCart editQuantity(@RequestBody ShoppingCart shoppingCart, @PathVariable Long id) {
        Optional<ShoppingCart>shoppingCartData = shoppingCartRepository.findById(id);
        if(shoppingCartData.isPresent()) {
            ShoppingCart _shoppingCart = shoppingCartData.get();
            for (int i = 0; i < _shoppingCart.getProducts().size(); i++){
                _shoppingCart.getProducts().get(i).setQuantity(shoppingCart.getProducts().get(i).getQuantity());
            }
            return shoppingCartRepository.save(_shoppingCart);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShoppinCartItem(@PathVariable Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
