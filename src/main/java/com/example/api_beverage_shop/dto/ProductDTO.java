package com.example.api_beverage_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    private String productName;
    private BigDecimal priceDefault;
    private String description;
    private Integer quantity;
    private Long categoryId;
    //    private MultipartFile productImages;
    private String pathImage;
    private Float rating;
    private Boolean isSaveCloud = true;
    private Integer status;
}
