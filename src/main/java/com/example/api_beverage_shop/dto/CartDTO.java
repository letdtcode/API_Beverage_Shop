package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.dto.response.CartItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long Id;
    private List<CartItemResponse> cartItems;
    private BigDecimal totalPrice;
}
