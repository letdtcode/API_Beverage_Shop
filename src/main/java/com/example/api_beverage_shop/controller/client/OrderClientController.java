package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.OrderDTO;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class OrderClientController {
    @Autowired
    private final IOrderService orderService;

    @PostMapping("/order/checkout")
    public ResponseEntity<OrderDTO> checkOutInCart(@RequestBody CheckOutCartRequest request) {
        OrderDTO orderDTO = orderService.checkOut(request);
        return ResponseEntity.ok(orderDTO);
    }
}
