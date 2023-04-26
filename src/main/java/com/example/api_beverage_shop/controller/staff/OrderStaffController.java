package com.example.api_beverage_shop.controller.staff;

import com.example.api_beverage_shop.dto.response.order.OrderResponse;
import com.example.api_beverage_shop.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class OrderStaffController {

    @Autowired
    private final IOrderService orderService;

    @PostMapping("/order/approve")
    public ResponseEntity<OrderResponse> approveOrder(@Param("orderId") Long orderId, @Param("status") Integer status) {
        OrderResponse orderResponse = orderService.approveOrder(orderId, status);
        return ResponseEntity.ok(orderResponse);
    }
}
