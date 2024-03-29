package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.OrderItemRepository;
import com.example.IPRWC.Backend.repository.OrderRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import com.example.IPRWC.Backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:4200", "http://93.119.4.217", "http://s1108697.nl"}, allowCredentials = "true")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderService orderService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return this.orderService.getOrderbyId(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getOrdersByUser(Authentication authentication) {
        return orderService.getAllOrdersFromUser(authentication);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order, Authentication authentication) {
        return orderService.addOrder(order, authentication);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderService.removeOrder(id);
    }

}
