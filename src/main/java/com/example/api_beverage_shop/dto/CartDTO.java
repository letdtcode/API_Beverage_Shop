package com.example.api_beverage_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> cartItems;
    private BigDecimal totalPrice;
}
