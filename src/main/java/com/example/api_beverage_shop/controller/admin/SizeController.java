package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.SizeDTO;
import com.example.api_beverage_shop.service.size.ISizeService;
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
public class SizeController {
    @Autowired
    private final ISizeService sizeService;

    @PostMapping("/sizes")
    public ResponseEntity<SizeDTO> createSize(@RequestBody SizeDTO sizeDTO) {
        return ResponseEntity.ok(sizeService.createSize(sizeDTO));
    }
}
