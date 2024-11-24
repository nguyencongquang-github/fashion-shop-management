package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.entity.Product;
import com.example.fashion_shop_management.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // API createProductSize product
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam Integer categoryId,
                                                 @RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestParam MultipartFile imageUrl,
                                                 @RequestParam BigDecimal importPrice,
                                                 @RequestParam BigDecimal retailPrice,
                                                 @RequestParam List<String> sizes,
                                                 @RequestParam Integer quantity) {
        Product product = productService.createProduct(categoryId, name, description, imageUrl, importPrice, retailPrice, sizes, quantity);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // API update product
    @PutMapping("update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestParam Integer categoryId,
                                                 @RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestParam(required = false) MultipartFile imageUrl,
                                                 @RequestParam BigDecimal importPrice,
                                                 @RequestParam BigDecimal retailPrice,
                                                 @RequestParam List<String> sizes,
                                                 @RequestParam Integer quantity) {
        Product updatedProduct = productService.updateProduct(productId, categoryId, name, description, imageUrl, importPrice, retailPrice, sizes, quantity);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // API delete product
    @DeleteMapping("delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // API view product detail
    @GetMapping("view-details/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // API get all products with pagination
    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam Integer page,
                                                              @RequestParam Integer size) {
        Map<String, Object> response = productService.getAllProducts(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API search products
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // API get all products by category
    @GetMapping("/get-by-category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
