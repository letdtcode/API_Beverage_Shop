package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.util.AppConstant;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.ICategoryRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICategoryRepository categoryRepository;
    private final Converter<Category, Long> categoryToCategoryIdConverter = context -> {
        Category category = context.getSource();
        return category != null ? category.getId() : null;
    };
    private final Converter<Long, Category> categoryIdToCategoryConverter = mappingContext -> {
        Long id = mappingContext.getSource();
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND + id));
        return category;
    };

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(Product.class, ProductDTO.class)
                .addMappings(mapper -> mapper.using(categoryToCategoryIdConverter)
                        .map(Product::getCategory, ProductDTO::setCategoryId));
        mapper.createTypeMap(ProductDTO.class, Product.class)
                .setPropertyCondition(Conditions.isNotNull())
                .addMappings(mapper -> mapper.using(categoryIdToCategoryConverter)
                        .map(ProductDTO::getCategoryId, Product::setCategory));
    }

    public ProductDTO toDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO dto) {
        return mapper.map(dto, Product.class);
    }
}
