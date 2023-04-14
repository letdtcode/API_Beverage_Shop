package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingDTO {
    private Long Id;
    private String toppingName;
    private Integer toppingPrice;
}
