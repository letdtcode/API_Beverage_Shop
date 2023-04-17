package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    public Optional<Cart> findCartById(Long Id);
}
