package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    public List<ProductDTO> getAllProducts();
    public ProductDTO getProduct(Long Id);

    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile file) throws Exception;

    public ProductDTO updateImageForProduct(Long Id, MultipartFile file) throws Exception;

    public ProductDTO updateInfoForProduct(ProductDTO productDTO, Long id) throws Exception;
}
