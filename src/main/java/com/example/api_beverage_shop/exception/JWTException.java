package com.example.api_beverage_shop.exception;

import io.jsonwebtoken.MalformedJwtException;

public class JWTException extends MalformedJwtException {
    public JWTException(String message) {
        super(message);
    }
}
