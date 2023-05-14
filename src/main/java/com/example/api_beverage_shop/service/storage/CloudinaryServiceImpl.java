package com.example.api_beverage_shop.service.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.api_beverage_shop.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryServiceImpl implements ICloudinaryService {
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public String store(MultipartFile file) {
        try {
            Map r = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String imgUrl = (String) r.get("secure_url");
            return imgUrl;
        } catch (IOException exception) {
            throw new StorageException("Failed to store file: ", exception);
        }
    }



}
