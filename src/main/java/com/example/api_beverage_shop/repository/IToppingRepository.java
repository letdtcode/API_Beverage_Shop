package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IToppingRepository extends JpaRepository<Topping, Long> {
    public Topping findByToppingName(String toppingName);
}
