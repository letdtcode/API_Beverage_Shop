package com.example.api_beverage_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class DiscountDTO {
    private String discountCode;
    private String imageDemo;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private BigDecimal discountValue;
    private String description;
    private int quantity;
}
