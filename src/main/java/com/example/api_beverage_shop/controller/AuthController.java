package com.example.api_beverage_shop.controller;

import com.example.api_beverage_shop.dto.request.auth.LoginRequest;
import com.example.api_beverage_shop.dto.request.auth.RegisterRequest;
import com.example.api_beverage_shop.dto.request.auth.TokenRefreshRequest;
import com.example.api_beverage_shop.security.auth.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
