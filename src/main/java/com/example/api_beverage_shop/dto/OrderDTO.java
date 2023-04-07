package com.example.api_beverage_shop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderDTO {
    private String nameCustomer;
    private String phoneNumber;
    private String address;
    private int shipping;
    private int payment;
    private BigDecimal totalItemPrice;
    private BigDecimal totalPrice;
    private DiscountDTO discount;
    private UserDTO user;
    private List<OrderItemDTO> orderItems;
}
