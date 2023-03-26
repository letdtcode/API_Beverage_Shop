package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
