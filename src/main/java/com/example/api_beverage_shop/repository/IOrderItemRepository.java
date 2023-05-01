package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByOrder_Id(Long orderId);
}
