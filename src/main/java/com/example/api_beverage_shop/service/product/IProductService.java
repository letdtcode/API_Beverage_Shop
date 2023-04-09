package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    public List<ProductDTO> getAllProducts();
    public ProductDTO getProductById(Long Id);

    public ProductDTO getProductByName(String name);

    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile file);

    public ProductDTO updateImageForProduct(Long Id, MultipartFile file);

    public ProductDTO updateInfoForProduct(ProductDTO productDTO, Long id);

    String getPathImgProduct(Long Id);
}
