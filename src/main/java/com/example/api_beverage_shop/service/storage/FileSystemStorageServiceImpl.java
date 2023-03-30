package com.example.api_beverage_shop.service.storage;

import com.example.api_beverage_shop.config.StorageProperties;
import com.example.api_beverage_shop.exception.StorageException;
import org.springframework.core.io.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageServiceImpl implements IStorageService {
    private final Path rootLocation;
    @Override
    public String getStorageFilename(MultipartFile file, String id) {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id + "." + ext;
    }
    public FileSystemStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
    @Override
    public void store(MultipartFile file, String storeFilename) throws IOException {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                    .normalize().toAbsolutePath(); //lấy đường dẫn tuyệt đối
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Can not store file outside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new StorageException("Failed to store file: ", e);
        }
    }
    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new StorageException("Can not read file: " + fileName);
        } catch (Exception e) {
            throw new StorageException("Could not read file: " + fileName);
        }
    }
    @Override
    public Path load (String fileName) {
        return rootLocation.resolve(fileName);
    }
    @Override
    public void delete(String storeFileName) throws IOException {
        Path destinationFile = rootLocation.resolve(Paths.get(storeFileName)).normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new StorageException("Could not read file: ", e);
        }
    }
}
