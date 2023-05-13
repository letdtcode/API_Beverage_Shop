package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    public Optional<Notification> findById(Long Id);

    public List<Notification> findByStatus(Integer status);
}
