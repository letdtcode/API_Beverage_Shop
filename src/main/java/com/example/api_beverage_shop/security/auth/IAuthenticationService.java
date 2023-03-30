package com.example.api_beverage_shop.security.auth;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.LoginRequest;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.dto.request.TokenRefreshRequest;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationService {
    public AuthResponse authenticate(LoginRequest request);

    public UserDTO register(@RequestBody RegisterRequest request);

//    AuthResponse refresh(TokenRefreshRequest request);
}
