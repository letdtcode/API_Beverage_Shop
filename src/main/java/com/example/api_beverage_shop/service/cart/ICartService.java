package com.example.api_beverage_shop.service.cart;

import com.example.api_beverage_shop.dto.CartDTO;
import com.example.api_beverage_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartService {
    CartDTO creatNewProductInCart(Long userId, String productName, Integer quantity, List<String> toppingNameList, String sizeName);
}
