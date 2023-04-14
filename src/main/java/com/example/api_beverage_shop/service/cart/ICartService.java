package com.example.api_beverage_shop.service.cart;

import com.example.api_beverage_shop.dto.request.AddCartRequest;
import com.example.api_beverage_shop.dto.response.CartDTO;

import java.util.List;

public interface ICartService {
    public CartDTO creatNewProductInCart(AddCartRequest cartRequest);
}
