package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWishListRepository extends JpaRepository<WishList, Long> {
}
