package com.example.api_beverage_shop.controller.staff;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class ProductStaffController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getInfoAllProduct() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/product/changestatus")
    public ResponseEntity<ProductDTO> changeStatusOfProduct(@Param("productName") String productName,
                                                            @Param("status") Integer status) {
        ProductDTO productDTO = productService.changeStatusProduct(productName, status);
        return ResponseEntity.ok(productDTO);
    }
}
