package com.example.api_beverage_shop.exception;

public class StorageFileNotFoundException extends StorageException{

    private static final long serialVersionUID = 1L;
    public StorageFileNotFoundException(String message) {
        super(message);
    }
}
