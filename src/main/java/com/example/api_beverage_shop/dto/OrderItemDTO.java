package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.model.Order;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.model.Size;
import com.example.api_beverage_shop.model.Topping;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemDTO {
    private ProductDTO product;
    private OrderDTO order;
    private List<Topping> toppings;
    private SizeDTO sizeProduct;
    private int quantity;
    private BigDecimal totalPriceProduct;
    private BigDecimal totalPriceItem;
}
