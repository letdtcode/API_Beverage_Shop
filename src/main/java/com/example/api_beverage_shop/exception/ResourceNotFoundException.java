package com.example.api_beverage_shop.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
