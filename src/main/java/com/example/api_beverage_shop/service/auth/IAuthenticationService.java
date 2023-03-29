package com.example.api_beverage_shop.service.auth;

import com.example.api_beverage_shop.dto.request.AuthRequest;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.dto.request.TokenRefreshRequest;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationService {
    public AuthResponse authenticate(AuthRequest request);

    public AuthResponse register(@RequestBody RegisterRequest request);

    AuthResponse refresh(TokenRefreshRequest request);
}
