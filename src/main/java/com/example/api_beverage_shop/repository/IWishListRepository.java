package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWishListRepository extends JpaRepository<WishList, Long> {
    public Optional<WishList> findWishListById(Long Id);
}
