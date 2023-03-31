package com.example.api_beverage_shop.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IStorageService {
    public String getStorageFilename(MultipartFile file, String id);

    public void store(MultipartFile file, String storeFilename);

    public Resource loadAsResource(String fileName);

    public Path load(String fileName);

    public void delete(String storeFileName) throws IOException;

    public void init();
}
