package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDiscountRepository extends JpaRepository<Discount, Long> {
    public Optional<Discount> findByDiscountCode(String code);
}
