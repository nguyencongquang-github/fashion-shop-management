package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.service.interf.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Create product successfully")
                .data(productService.addProduct(productDto))
                .build();
    }

    @PutMapping("/update/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer productId) {
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Update product successfully")
                .data(productService.updateProduct(productId, productDto))
                .build();
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Boolean> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<Boolean>builder()
                .code(200)
                .message("Delete product successfully")
                .data(true)
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<ProductDto>> getAllProducts() {
        return ApiResponse.<List<ProductDto>>builder()
                .code(200)
                .message("Get all products successfully")
                .data(productService.getAllProducts())
                .build();
    }

    @GetMapping("/get-product/{productId}")
    public ApiResponse<ProductDto> getProduct(@PathVariable Integer productId) {
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Get product successfully")
                .data(productService.getProductInfo(productId))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductDto>> searchProduct(@RequestParam String searchValue) {
        return ApiResponse.<List<ProductDto>>builder()
                .code(200)
                .message("Search product successfully")
                .data(productService.searchProduct(searchValue))
                .build();
    }
}
