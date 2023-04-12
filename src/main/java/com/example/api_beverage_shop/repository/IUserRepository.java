package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByMail(String email);

    public boolean existsByMail(String mail);

    public Optional<User> findById(Long Id);
}
