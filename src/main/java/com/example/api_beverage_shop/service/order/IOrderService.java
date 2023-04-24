package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.response.order.OrderDTO;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;

public interface IOrderService {
    public OrderDTO checkOut(CheckOutCartRequest request);

    OrderDTO approveOrder(Long orderId, Integer status);
}
