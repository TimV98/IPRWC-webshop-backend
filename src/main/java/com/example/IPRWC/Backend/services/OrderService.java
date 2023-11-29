package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Order;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.dto.OrderDTO;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.OrderRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            OrderDTO orderDTO = OrderDTO.builder().order(order).build();
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No Order with that ID found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllOrdersFromUser(Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isPresent()) {
            User user = userRepository.findByEmail(authentication.getName()).get();
            String email = user.getEmail();
            List<Order> orders = orderRepository.findAllByUserEmail(email);
            List<OrderDTO> userOrderList = new ArrayList<>();
            for (var order : orders) {
                OrderDTO orderDTO = OrderDTO.builder().order(order).build();

                userOrderList.add(orderDTO);
            }
            return new ResponseEntity<>(userOrderList, HttpStatus.OK);
        }
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "User not authenticated to get access to this resource", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No Orders found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addOrder(Order order, Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isPresent()) {
            User user = userRepository.findByEmail(authentication.getName()).get();
            order.setUser(user);
            orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "You have to be authenticated to post an order", HttpStatus.FORBIDDEN.name()), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Something went wrong", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> removeOrder(long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new MessageResponse("Order removed!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Order with id" + id + " does not exist", HttpStatus.UNPROCESSABLE_ENTITY.name()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
