package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.ShoppingCart;
import com.example.IPRWC.Backend.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserEmail(String email);

}
