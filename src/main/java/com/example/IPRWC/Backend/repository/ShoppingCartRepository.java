package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserEmail(String email);
}
