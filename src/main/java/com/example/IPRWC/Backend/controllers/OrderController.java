package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.ShoppingCartItem;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.repository.ShoppingCartItemRepository;
import com.example.IPRWC.Backend.repository.ShoppingCartRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @GetMapping("/get/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return shoppingCartRepository.findById(id);
    }

    @GetMapping("/get")
    public Optional<Order> getOrderByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        return shoppingCartRepository.findByUserEmail(email);
    }

    @GetMapping("/getAll")
    public List<Order> getOrdersByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        return shoppingCartRepository.findAllByUserEmail(email);
    }

    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        shoppingCartRepository.findByUserEmail(authentication.getName());
        if (user.isEmpty()) {
            order.setUser(null);
            return shoppingCartRepository.save(order);
        }
        order.setUser(user.get());
        return shoppingCartRepository.save(order);
    }

    @PutMapping("/edit/{id}")
    public Order editQuantity(@RequestBody Order order, @PathVariable Long id) {
        Optional<Order> shoppingCartData = shoppingCartRepository.findById(id);
        if (shoppingCartData.isPresent()) {
            Order _order = shoppingCartData.get();
            for (int i = 0; i < _order.getProducts().size(); i++) {
                _order.getProducts().get(i).setQuantity(order.getProducts().get(i).getQuantity());
            }
            return shoppingCartRepository.save(_order);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        Optional<Order> _shoppingcart = shoppingCartRepository.findById(id);
        Order order = _shoppingcart.get();
        order.setUser(null);
        shoppingCartRepository.save(order);
        shoppingCartRepository.deleteById(id);
    }

}
