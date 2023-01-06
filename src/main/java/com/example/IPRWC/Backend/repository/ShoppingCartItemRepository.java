package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}
