package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.ProductMapper;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.service.storage.IStorageService;
import com.example.api_beverage_shop.config.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IStorageService storageService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }
    @Override
    public ProductDTO getProductById(Long Id) {
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + Id));
        return productMapper.toDTO(product);
    }
    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepository.findByProductName(name).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + name));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile file) {
        //Luu anh san pham
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        String path = storageService.getStorageFilename(file, id);
        storageService.store(file, path);

        Product product = productMapper.toEntity(productDTO);
        product.setPathImage(path);
        product = productRepository.save(product);

        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateImageForProduct(Long Id, MultipartFile file) {
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + Id));
        if (!file.isEmpty()) {
            //Luu anh san pham
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            String path = storageService.getStorageFilename(file, id);
            storageService.store(file, path);

            product.setPathImage(path);
            productRepository.save(product);
            return productMapper.toDTO(product);
        }
        throw new ResourceNotFoundException(AppConstant.FILE_IS_NULL);
    }

    @Override
    public ProductDTO updateInfoForProduct(ProductDTO productDTO, Long Id) {
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + Id));

        Product enityCovert = productMapper.toEntity(productDTO);
        mapper.map(enityCovert,product);
        productRepository.save(product);
        return productMapper.toDTO(product);
    }
}