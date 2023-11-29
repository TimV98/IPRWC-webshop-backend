package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserEmail(String email);
    List<Order> findAllByUserEmail(String email);


}
