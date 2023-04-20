package com.example.api_beverage_shop.dto.request.wish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWishRequest {
    private Long userId;
    private String productName;
}
