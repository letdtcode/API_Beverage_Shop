package com.example.api_beverage_shop.service.product;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.exception.ResourceExistException;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.ProductMapper;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.service.storage.ICloudinaryService;
import com.example.api_beverage_shop.service.storage.IStorageService;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
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

    @Autowired
    private ICloudinaryService cloudinaryService;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream().map(
                product -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }


    @Override
    public List<ProductDTO> getProductsByCategoryName(String categoryName) {
        List<Product> products = productRepository.findByCategory_CategoryName(categoryName);
        Iterator<Product> productList = products.iterator();
        while (productList.hasNext()) {
            Product item = productList.next();
            if (item.getStatus() == 0) {
                productList.remove();
            }
        }
        List<ProductDTO> productDTOList = products.stream().map(product
                -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllProductsCurrentUse() {
        List<Product> products = productRepository.findAll();
        Iterator<Product> productList = products.iterator();
        while (productList.hasNext()) {
            Product item = productList.next();
            if (item.getStatus() == 0) {
                productList.remove();
            }
        }
        List<ProductDTO> productDTOList = products.stream().map(product ->
                productMapper.toDTO(product)).collect(Collectors.toList());
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
        Product product = productRepository.findByProductName(name).orElseThrow(()
                -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + name));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile file) {
        Product product = productMapper.toEntity(productDTO);
        if (productDTO.getIsSaveCloud()) {
//            Luu tren cloud
            String pathImage = cloudinaryService.store(file);
            product.setPathImage(pathImage);
        } else {
//            Luu trong file system
            //Luu anh san pham
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            String path = storageService.getStorageFilename(file, id);
            product.setPathImage(path);
            storageService.store(file, path);
        }
        String productName = product.getProductName();
        if (productRepository.existsByProductName(productName)) {
            throw new ResourceExistException(AppConstant.PRODUCT_EXIST_WITH_NAME);
        }
        product.setSoldCount(0);
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
        mapper.map(enityCovert, product);
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    public String getPathImgProduct(Long Id) {
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + Id));

        String pathImg = product.getPathImage();
        return pathImg;
    }

    @Override
    public String getPathImgProductByName(String productName) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + productName));
        String pathImg = product.getPathImage();
        return pathImg;
    }

    @Override
    public ProductDTO changeStatusProduct(String productName, Integer status) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + productName));
        product.setStatus(status);
        productRepository.save(product);
        return productMapper.toDTO(product);
    }
}
