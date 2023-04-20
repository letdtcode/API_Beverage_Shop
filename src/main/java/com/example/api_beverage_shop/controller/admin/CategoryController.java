package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.CategoryDTO;
import com.example.api_beverage_shop.dto.ProductDTO;
import com.example.api_beverage_shop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam("name") String nameCategory) {
        return ResponseEntity.ok(categoryService.getCategoryByName(nameCategory));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO, id));
    }

    @GetMapping("/{category_id}/products")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryId(@PathVariable("category_id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getAllProductByCategoryId(categoryId));
    }

}
