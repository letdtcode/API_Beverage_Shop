package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.ProductMapper;
import com.example.api_beverage_shop.model.Category;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.ICategoryRepository;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.service.storage.IStorageService;
import com.example.api_beverage_shop.util.AppConstant;
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

    private ICategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile file) {
        Product product = Product.builder().build();
        //Luu anh san pham
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        String path = storageService.getStorageFilename(file, id);
        storageService.store(file, path);

        product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProduct(Long Id) {
        Product product = productRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateImageForProduct(Long Id, MultipartFile file) throws Exception {
        if (Id != null) {
            Product product = productRepository.findById(Id)
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
            if (!file.isEmpty()) {
                //Luu anh san pham
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString();
                String path = storageService.getStorageFilename(file, id);
                storageService.store(file, path);

                product.setPathImage(path);
                productRepository.save(product);
            }
            return productMapper.toDTO(product);
        } else throw new Exception(AppConstant.ID_IS_NULL);
    }

    @Override
    public ProductDTO updateInfoForProduct(ProductDTO productDTO, Long id) throws Exception {
        if (id != null) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
            Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
            product.setCategory(category);
            productRepository.save(product);
            return productMapper.toDTO(product);
        } else throw new Exception(AppConstant.ID_IS_NULL);
    }

}
