package com.example.api_beverage_shop.controller;

import com.example.api_beverage_shop.dto.request.*;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import com.example.api_beverage_shop.security.auth.IAuthenticationService;
import com.example.api_beverage_shop.service.product.IProductService;
import com.example.api_beverage_shop.service.storage.IStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

//    @Autowired
//    private final IProductService productService;
//
//    @Autowired
//    private final IStorageService storageService;
    @Autowired
    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }

//    @GetMapping("/images/products/{Id}")
//    @ResponseBody
//    public ResponseEntity<Resource> getProductImage(@PathVariable Long Id, HttpServletRequest request) {
//        String filename = productService.getPathImgProduct(Id);
//        Resource file = storageService.loadAsResource(filename);
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            System.out.println("Could not determine fileType");
//        }
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
//    }
}
