package com.example.api_beverage_shop.service.category;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {
    public CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long Id);

    List<ProductDTO> getAllProductByCategoryId(Long Id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) throws Exception;

    CategoryDTO updateImageForCategory(Long Id, MultipartFile file);
}
