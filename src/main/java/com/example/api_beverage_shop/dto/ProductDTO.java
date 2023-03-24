package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.model.Category;

import java.math.BigDecimal;

public class ProductDTO {
    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private int quantity;
    private CategoryDTO category;
}
