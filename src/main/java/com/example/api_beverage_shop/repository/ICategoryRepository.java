package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String string);

    public Boolean existsByCategoryName(String categoryName);
}
