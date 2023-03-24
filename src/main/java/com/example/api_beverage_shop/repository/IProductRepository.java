package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
