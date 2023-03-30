package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.mapper.ProductMapper;
import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.IProductRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }

    public

}
