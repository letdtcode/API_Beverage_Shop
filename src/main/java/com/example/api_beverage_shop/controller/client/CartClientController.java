package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.response.cart.CartItemResponse;
import com.example.api_beverage_shop.dto.request.cart.AddCartRequest;
import com.example.api_beverage_shop.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class CartClientController {

    @Autowired
    private final ICartService cartService;

    @PostMapping("/cart/addproduct")
    public ResponseEntity<CartItemResponse> creatNewProductInCart(@RequestBody AddCartRequest addCartRequest) {
        return ResponseEntity.ok(cartService.creatNewProductInCart(addCartRequest));
    }

    @GetMapping("/cart/items")
    public ResponseEntity<List<CartItemResponse>> getAllCartItemInfo(@Param("userId") Long userId) {
        return ResponseEntity.ok(cartService.getAllCartItemInfo(userId));
    }
}
