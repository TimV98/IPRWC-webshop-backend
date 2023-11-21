package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.payload.response.OrderResponse;
import com.example.IPRWC.Backend.repository.OrderRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<?> getOrderbyId(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).get();
            return new ResponseEntity<>(new OrderResponse(order, "Order Found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No Order with that ID found"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllOrdersFromUser(Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isPresent()) {
            User userData = userRepository.findByEmail(authentication.getName()).get();
            String email = userData.getEmail();
            List<Order> orders = orderRepository.findAllByUserEmail(email);
            return new ResponseEntity<>(new OrderResponse(orders, "Orders for user found!"), HttpStatus.OK);
        }
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN, "User not found or not authenticated").getHttpStatus());
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No Orders found"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addOrder(Order order, Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isPresent()) {
            User user = userRepository.findByEmail(authentication.getName()).get();
            orderRepository.findByUserEmail(authentication.getName());
            order.setUser(user);
            orderRepository.save(order);
            OrderResponse orderResponse = OrderResponse.builder().message("Order added").order(order).build();
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN, "You have to be authenticated to post an order"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> removeOrder(long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new MessageResponse("Order removed!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Order with id" + id + " does not exist"),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
