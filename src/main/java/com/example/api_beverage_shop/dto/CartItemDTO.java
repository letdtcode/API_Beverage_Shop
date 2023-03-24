package com.example.api_beverage_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDTO {
    private int cartId;
    private ProductDTO product;
    private ToppingDTO topping;
    private SizeDTO size;
    private int quantity;
    private BigDecimal totalPriceItem;
}
