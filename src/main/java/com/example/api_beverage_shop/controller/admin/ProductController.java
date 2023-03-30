package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryId(@PathVariable("category_id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getAllProductByCategoryId(categoryId));
    }
}
