package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.service.topping.IToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ToppingClientController {

    @Autowired
    private final IToppingService toppingService;

    @GetMapping("/toppings")
    public ResponseEntity<?> getAllToppingInfo() {
        return ResponseEntity.ok(toppingService.getAllToppingInfo());
    }
}
