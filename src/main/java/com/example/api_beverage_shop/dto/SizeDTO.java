package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
    private Long Id;
    private String sizeName;
    private Integer pricePlus;
}
