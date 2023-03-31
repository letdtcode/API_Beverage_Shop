package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.service.product.IProductService;
import com.example.api_beverage_shop.service.storage.IStorageService;
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

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts (){
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct (@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestBody ProductDTO productDTO) throws Exception {
        return ResponseEntity.ok(productService.createProduct(productDTO, file));
    }
    @PutMapping("/product")
    public ResponseEntity<ProductDTO> updateImageForProduct(
            @RequestParam Long id,
            @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(productService.updateImageForProduct(id, file));
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateInfoForProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) throws Exception {
        return ResponseEntity.ok(productService.updateInfoForProduct(productDTO, id));
    }
}
