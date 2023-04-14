package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private int cartId;
    private ProductDTO product;
    private ToppingDTO topping;
    private SizeDTO size;
    private int quantity;
    private BigDecimal totalPriceItem;
}
