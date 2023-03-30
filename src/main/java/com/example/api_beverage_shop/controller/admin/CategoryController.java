package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.security.auth.IAuthenticationService;
import com.example.api_beverage_shop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{category_id}/products")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryId(@PathVariable("category_id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getAllProductByCategoryId(categoryId));
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> createCategory(
            @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }
    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }
    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> de(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }
}
