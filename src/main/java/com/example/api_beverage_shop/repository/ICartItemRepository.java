package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findCartItemByCart(Cart cart);
    public Optional<CartItem> findCartItemById(Long Id);
}
