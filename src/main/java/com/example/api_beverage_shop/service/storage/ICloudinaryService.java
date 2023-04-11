package com.example.api_beverage_shop.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
    public String store(MultipartFile file);
}
