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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Category> category = categoryRepository.findByCategoryName(name);
        if (category.isPresent()) {
            return mapper.map(category.get(), CategoryDTO.class);
        }
        throw new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND_WITH_NAME + name);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList =
                categories.stream().map(cate -> mapper.map(cate, CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(Long Id) {
        Optional<Category> category = categoryRepository.findById(Id);
        if (category.isPresent()) {
            return mapper.map(category.get(), CategoryDTO.class);
        }
        throw new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + Id);
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(Long Id) {
        Category category = categoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + Id));
        Optional<List<Product>> productList = productRepository.findByCategory(category);
        return productList.get().stream().map(product -> mapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder().build();
        BeanUtils.copyProperties(categoryDTO, category);
        category = categoryRepository.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) throws Exception {

        Category entity = Category.builder().build();
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            BeanUtils.copyProperties(category.get(), entity);
            mapper.map(categoryDTO, entity);
            entity = categoryRepository.save(entity);
            return mapper.map(entity, CategoryDTO.class);
        } else throw new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + id);
    }


}
