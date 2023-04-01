package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.service.product.IProductService;
import com.example.api_beverage_shop.service.storage.IStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProductByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("model") String JsonObject) throws JsonProcessingException {
        ProductDTO product = ProductDTO.builder().build();
        product = objectMapper.readValue(JsonObject, ProductDTO.class);
        return ResponseEntity.ok(productService.createProduct(product, file));
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> updateImageForProduct(
            @RequestParam("id") Long id,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(productService.updateImageForProduct(id, file));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateInfoForProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateInfoForProduct(productDTO, id));
    }
}
