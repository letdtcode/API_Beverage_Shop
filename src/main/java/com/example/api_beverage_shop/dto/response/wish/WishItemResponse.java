package com.example.api_beverage_shop.dto.response.wish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishItemResponse {
    private Long Id;
    private String productName;
    private Long wishListId;
    private int status;
}
