package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
