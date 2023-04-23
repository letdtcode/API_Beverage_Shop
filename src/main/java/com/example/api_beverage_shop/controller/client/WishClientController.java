package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.request.wish.AddWishRequest;
import com.example.api_beverage_shop.service.wish.IWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class WishClientController {

    @Autowired
    private IWishService wishService;

    @PostMapping("/wishhandle")
    public ResponseEntity<?> handleWishItem(@RequestBody AddWishRequest addWishRequest) {
        return ResponseEntity.ok(wishService.createWishItem(addWishRequest));
    }

    @GetMapping("/wishitems")
    public ResponseEntity<?> getAllWishItemOfUser(@Param("userId") Long userId) {
        return ResponseEntity.ok(wishService.getAllWishItemOfUser(userId));
    }

    @GetMapping("/check/wishitem")
    public ResponseEntity<?> checkProductIsWishItem(@Param("productName") String productName, @Param("userId") Long userId) {
        return ResponseEntity.ok(wishService.checkProductIsWishItem(productName, userId));
    }
}
