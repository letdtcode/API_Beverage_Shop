package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWishListRepository extends JpaRepository<WishList, Long> {
    public Optional<WishList> findWishListById(Long Id);
}
