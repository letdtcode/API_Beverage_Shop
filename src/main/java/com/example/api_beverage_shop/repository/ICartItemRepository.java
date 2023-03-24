package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
}
