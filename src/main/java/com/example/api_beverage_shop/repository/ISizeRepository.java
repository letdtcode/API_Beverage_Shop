package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ISizeRepository extends JpaRepository<Size, Long> {
    public Optional<Size> findBySizeName(String sizeName);
}
