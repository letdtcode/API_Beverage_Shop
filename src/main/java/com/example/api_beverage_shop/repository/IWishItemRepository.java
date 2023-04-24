package com.example.api_beverage_shop.repository;

import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.model.WishItem;
import com.example.api_beverage_shop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWishItemRepository extends JpaRepository<WishItem, Long> {

    @Query("SELECT w FROM WishItem w WHERE w.product = :product AND w.wishList = :wishList")
    public Optional<WishItem> findByProductAndWishList(@Param("product") Product product, @Param("wishList") WishList wishList);
}
