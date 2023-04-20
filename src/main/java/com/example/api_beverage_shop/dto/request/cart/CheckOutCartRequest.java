package com.example.api_beverage_shop.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutCartRequest {
    private Long userId;
    private String address;
    private String nameCus;
    private int payMent;
    private String phoneNumber;
    private Integer shipping;
    private BigDecimal totalItemPrice;
    private BigDecimal totalPrice;
//    private Long discountId;
}

