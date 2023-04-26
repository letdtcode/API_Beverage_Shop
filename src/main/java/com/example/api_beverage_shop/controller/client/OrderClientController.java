package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.response.order.OrderItemResponse;
import com.example.api_beverage_shop.dto.response.order.OrderResponse;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class OrderClientController {
    @Autowired
    private final IOrderService orderService;

    @PostMapping("/order/checkout")
    public ResponseEntity<OrderResponse> checkOutInCart(@RequestBody CheckOutCartRequest request) {
        OrderResponse orderResponse = orderService.checkOut(request);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItemResponse>> getAllListOrderItems(@Param("userId") Long userId) {
        return ResponseEntity.ok(orderService.getAllListOrderItem(userId));
    }

}
