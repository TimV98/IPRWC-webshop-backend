package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
