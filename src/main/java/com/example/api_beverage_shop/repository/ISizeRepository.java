package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.dto.SizeDTO;
import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISizeRepository extends JpaRepository<Size, Long> {
    public Optional<Size> findBySizeName(String sizeName);
}
