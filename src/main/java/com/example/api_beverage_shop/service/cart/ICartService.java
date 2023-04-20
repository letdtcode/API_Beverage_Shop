package com.example.api_beverage_shop.service.cart;

import com.example.api_beverage_shop.dto.response.CartItemResponse;
import com.example.api_beverage_shop.dto.request.AddCartRequest;

import java.util.List;

public interface ICartService {
    public CartItemResponse creatNewProductInCart(AddCartRequest cartRequest);

    List<CartItemResponse> getAllCartItemInfo(Long userId);
}
