package com.example.api_beverage_shop.service.category;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.ICategoryRepository;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByCategoryName(name).get();
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList =
                Arrays.asList(mapper.map(categories, CategoryDTO[].class));
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(Long Id) {
        return mapper.map(categoryRepository.findById(Id), CategoryDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(Long Id) {
        Category category = categoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + Id));
        List<Product> productList = productRepository.findByCategory(category);
        return Arrays.asList(mapper.map(productList, ProductDTO[].class));
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Long id = categoryDTO.getId();
        Category category = Category.builder().build();
        if (id != null) {
            Category oldCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + id));
            BeanUtils.copyProperties(oldCategory, category);
            BeanUtils.copyProperties(categoryDTO, category);
        } else {
            BeanUtils.copyProperties(categoryDTO, category);
        }
        category = categoryRepository.save(category);
        return mapper.map(category, CategoryDTO.class);
    }
}
