package com.example.api_beverage_shop;

import com.cloudinary.Cloudinary;
import com.example.api_beverage_shop.config.StorageProperties;
import com.example.api_beverage_shop.service.storage.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.HashMap;
import java.util.Map;


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

    @Bean
    public Cloudinary cloudinary() {
        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "dyv5zrsgj");
        config.put("api_key", "725286843731793");
        config.put("api_secret", "vlXQhzYH_ngfJqQHo5As0LAC0gc");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
