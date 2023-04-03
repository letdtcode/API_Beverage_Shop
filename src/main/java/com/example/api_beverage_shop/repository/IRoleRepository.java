package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByRoleName(String name);
}
