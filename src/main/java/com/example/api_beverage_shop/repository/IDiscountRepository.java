package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Long> {
    public Optional<Discount> findByDiscountCode(String code);
}
