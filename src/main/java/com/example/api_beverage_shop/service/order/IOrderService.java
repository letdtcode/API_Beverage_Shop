package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.dto.response.order.OrderItemResponse;
import com.example.api_beverage_shop.dto.response.order.OrderResponse;

import java.util.List;

public interface IOrderService {
    public OrderResponse checkOut(CheckOutCartRequest request);

    public OrderResponse approveOrder(Long orderId, Integer status);

    public List<OrderItemResponse> getAllListOrderItems(Long orderId);

    public List<OrderResponse> getAllListOrderOfUser(Long userId);

    List<OrderResponse> getListOrderWaitingDeliveryOfUser(Long userId);

    List<OrderResponse> getListOrderSuccessOfUser(Long userId);

    List<OrderResponse> getListOrderWaitingConfirmOfUser(Long userId);

    List<OrderResponse> getListOrderCancelOfUser(Long userId);
}
