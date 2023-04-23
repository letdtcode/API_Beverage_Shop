package com.example.api_beverage_shop.dto.response.wish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishItemResponse {
    private Long Id;
    private String productName;
    private String categoryName;
    private BigDecimal priceProduct;
    private String pathImg;
    private Float rating;
    private Long wishListId;
    private int status;
}
