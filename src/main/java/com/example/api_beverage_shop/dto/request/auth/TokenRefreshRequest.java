package com.example.api_beverage_shop.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {
    @NotBlank
    private String tokenRefresh;
}
