package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.response.order.OrderItemResponse;
import com.example.api_beverage_shop.dto.response.order.OrderResponse;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;

import java.util.List;

public interface IOrderService {
    public OrderResponse checkOut(CheckOutCartRequest request);

    public OrderResponse approveOrder(Long orderId, Integer status);

    public List<OrderItemResponse> getAllListOrderItem(Long userId);

}
