package com.example.api_beverage_shop.exception;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = 4L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Exception e) {
        super(message,e);
    }
}
