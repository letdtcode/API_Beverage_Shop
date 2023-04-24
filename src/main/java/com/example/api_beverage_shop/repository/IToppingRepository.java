package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface IToppingRepository extends JpaRepository<Topping, Long> {
    public Optional<Topping> findByToppingName(String toppingName);
}
