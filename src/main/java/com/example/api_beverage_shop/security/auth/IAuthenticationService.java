package com.example.api_beverage_shop.security.auth;

import com.example.api_beverage_shop.dto.request.auth.LoginRequest;
import com.example.api_beverage_shop.dto.request.auth.RegisterRequest;
import com.example.api_beverage_shop.dto.request.auth.TokenRefreshRequest;
import com.example.api_beverage_shop.dto.response.auth.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationService {
    AuthResponse register(@RequestBody RegisterRequest request);

    AuthResponse authenticate(LoginRequest request);

    AuthResponse refresh(TokenRefreshRequest request);
}
