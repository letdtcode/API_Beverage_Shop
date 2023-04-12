package com.example.api_beverage_shop.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class NotCredentialException extends BadCredentialsException {
    private static final long serialVersionUID = 1L;
    public NotCredentialException(String msg) {
        super(msg);
    }
}
