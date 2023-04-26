package com.example.api_beverage_shop.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long Id;
    private String productName;
    private Long orderId;
    private List<String> toppingsName;
    private String sizeName;
    private Integer quantity;
    private BigDecimal totalPriceProduct;
    private BigDecimal totalPriceItem;
    private Integer status;
}
