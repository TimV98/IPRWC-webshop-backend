package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.OrderItemRepository;
import com.example.IPRWC.Backend.repository.OrderRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @GetMapping("/get/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }

    @GetMapping("/get")
    public Optional<Order> getOrderByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        return orderRepository.findByUserEmail(email);
    }

    @GetMapping("/getAll")
    public List<Order> getOrdersByUser(Authentication authentication) {
        Optional<User> userData = userRepository.findByEmail(authentication.getName());
        String email = userData.get().getEmail();
        return orderRepository.findAllByUserEmail(email);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order, Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        orderRepository.findByUserEmail(authentication.getName());
        order.setUser(user.get());
        orderRepository.save(order);
        return ResponseEntity.ok(new MessageResponse("Order placed!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        if (orderRepository.findById(id).isPresent()) {
            Optional<Order> _order = orderRepository.findById(id);
            Order order = _order.get();
            order.setUser(null);
            orderRepository.save(order);
            orderRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Order Deleted"));
        }
        return ResponseEntity.unprocessableEntity().body(new MessageResponse("Order does not exist!"));
    }

}
