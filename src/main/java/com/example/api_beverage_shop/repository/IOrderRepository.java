package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findById(Long Id);

    public List<Order> findByUserOrder_Id(Long userId);
}
