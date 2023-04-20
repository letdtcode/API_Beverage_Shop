package com.example.api_beverage_shop.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartRequest {
    private Long userId;
    private String productName;
    private Integer quantity;
    private List<String> toppingNameList;
    private String sizeName;
}
