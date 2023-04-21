package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.request.wish.AddWishRequest;
import com.example.api_beverage_shop.service.wish.IWishService;
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
public class WishClientController {

    @Autowired
    private IWishService wishService;

    @PostMapping("/wishhandle")
    public ResponseEntity<?> handleWishItem(@RequestBody AddWishRequest addWishRequest) {
        return ResponseEntity.ok(wishService.createWishItem(addWishRequest));
    }
}
