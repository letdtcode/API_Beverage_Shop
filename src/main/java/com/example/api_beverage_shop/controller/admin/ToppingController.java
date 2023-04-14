package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.SizeDTO;
import com.example.api_beverage_shop.dto.ToppingDTO;
import com.example.api_beverage_shop.service.size.ISizeService;
import com.example.api_beverage_shop.service.topping.IToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ToppingController {
    @Autowired
    private final IToppingService toppingService;

    @PostMapping("/toppings")
    public ResponseEntity<ToppingDTO> createTopping(@RequestBody ToppingDTO toppingDTO) {
        return ResponseEntity.ok(toppingService.createTopping(toppingDTO));
    }
}
