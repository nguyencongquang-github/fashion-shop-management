package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.category.CategoryDto;
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.service.category.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }
//    public ApiResponse<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
//        return ApiResponse.<CategoryDto>builder()
//                .code(200)
//                .message("Create category successfully")
//                .data(categoryService.createCategory(categoryDto))
//                .build();
//    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
//    public ApiResponse<List<CategoryDto>> getAllCategories() {
//        return ApiResponse.<List<CategoryDto>>builder()
//                .code(200)
//                .message("Get all categories successfully")
//                .data(categoryService.getAllCategories())
//                .build();
//    }

    @PutMapping("/update/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }
//    public ApiResponse<CategoryDto> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) {
//        return ApiResponse.<CategoryDto>builder()
//                .code(200)
//                .message("Update category successfully")
//                .data(categoryService.updateCategory(categoryId, categoryDto))
//                .build();
//    }

    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(true);
    }
//    public ApiResponse<Boolean> deleteCategory(@PathVariable Integer categoryId) {
//        categoryService.deleteCategory(categoryId);
//        return ApiResponse.<Boolean>builder()
//                .code(200)
//                .message("Delete category successfully")
//                .data(true)
//                .build();
//    }

    @GetMapping("get-category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }
//    public ApiResponse<CategoryDto> getCategory(@PathVariable Integer categoryId) {
//        return ApiResponse.<CategoryDto>builder()
//                .code(200)
//                .message("Get category successfully")
//                .data(categoryService.getCategory(categoryId))
//                .build();
//    }
}
