package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long Id;
    private String productName;
    private List<String> toppingName;
    private String sizeName;
    private Integer quantity;
    private BigDecimal totalPriceProduct;
    private BigDecimal totalPriceItem;
    private Long cartId;
}
