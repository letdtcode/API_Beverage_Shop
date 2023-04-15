package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.CartItemDTO;
import com.example.api_beverage_shop.dto.request.AddCartRequest;
import com.example.api_beverage_shop.dto.response.CartDTO;
import com.example.api_beverage_shop.model.CartItem;
import com.example.api_beverage_shop.service.cart.ICartService;
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
public class CartClientController {

    @Autowired
    private final ICartService cartService;

    @PostMapping("/cart/addproduct")
    public ResponseEntity<CartItemDTO> creatNewProductInCart(@RequestBody AddCartRequest addCartRequest) {
//        ProductDTO product = ProductDTO.builder().build();
        return ResponseEntity.ok(cartService.creatNewProductInCart(addCartRequest));
    }
}
