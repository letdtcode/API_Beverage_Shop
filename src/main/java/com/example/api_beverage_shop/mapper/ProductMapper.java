package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.model.ProductImage;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper mapper;
    private final Converter<Category, Long> categoryToCategoryIdConverter = context -> {
        Category category = context.getSource();
        return category != null ? category.getCategoryId() : null;
    };
    private final Converter<ProductImage, String> productImageStringConverter = context -> {
        ProductImage productImage = context.getSource();
        return productImage != null ? productImage.getImageurl() : null;
    };

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Product.class, ProductDTO.class)
                .addMappings(mapper -> mapper.using(categoryToCategoryIdConverter)
                        .map(Product::getCategory, ProductDTO::setCategoryId))
                .addMappings(mapper -> mapper.using(productImageStringConverter)
                        .map(Product::getProductImagesUrl, ProductDTO::setUrlImages));
    }

    public ProductDTO toDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO dto) {
        return mapper.map(dto, Product.class);
    }
}
