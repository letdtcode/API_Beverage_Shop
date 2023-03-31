package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long Id;

    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private int quantity;
    private Long categoryId;

    private MultipartFile productImages;

    private String pathImage;
}
