package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    public Optional<List<Product>> findByCategory(Category category);

    public Optional<Product> findByProductName(String name);
}
