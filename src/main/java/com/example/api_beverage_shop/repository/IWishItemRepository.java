package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWishItemRepository extends JpaRepository<WishItem, Long> {
}
