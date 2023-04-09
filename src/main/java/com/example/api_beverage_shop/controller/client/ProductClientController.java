package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.service.product.IProductService;
import com.example.api_beverage_shop.service.storage.IStorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ProductClientController {
    @Autowired
    private final IProductService productService;

    @Autowired
    private final IStorageService storageService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getInfoAllProduct() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //    @GetMapping("/images/{filename:.+}")
//    public ResponseEntity<Resource> getProductImage(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+file.getFilename()+"\"").body(file);
//    }
    @GetMapping("/images/products/{Id}")
    @ResponseBody
    public ResponseEntity<Resource> getProductImage(@PathVariable Long Id, HttpServletRequest request) {
        String filename = productService.getPathImgProduct(Id);
        Resource file = storageService.loadAsResource(filename);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine fileType");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(file);
    }

//    @GetMapping("/images/{filename:.+}")
//    public ResponseEntity<Resource> getProductImage(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+file.getFilename()+"\"").body(file);
//    }
}
