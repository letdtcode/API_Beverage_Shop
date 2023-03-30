package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAllProducts();
}
