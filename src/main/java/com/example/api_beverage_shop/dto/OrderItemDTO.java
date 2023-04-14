package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.model.Topping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private ProductDTO product;
    private OrderDTO order;
    private List<Topping> toppings;
    private SizeDTO sizeProduct;
    private int quantity;
    private BigDecimal totalPriceProduct;
    private BigDecimal totalPriceItem;
}
