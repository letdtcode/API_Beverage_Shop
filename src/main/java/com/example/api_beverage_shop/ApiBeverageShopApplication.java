package com.example.api_beverage_shop;

import com.example.api_beverage_shop.config.StorageProperties;
import com.example.api_beverage_shop.service.storage.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ApiBeverageShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBeverageShopApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IStorageService storageService) {
        return (args -> {
            storageService.init();
        });
    }
    @Bean
    public StandardServletMultipartResolver multipartResolver () {
        return new StandardServletMultipartResolver();
    }

}
