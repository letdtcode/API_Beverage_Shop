package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    public Optional<List<Product>> findByCategory(Category category);

    public Optional<Product> findByProductName(String name);

    public Boolean existsByProductName(String productName);

}
