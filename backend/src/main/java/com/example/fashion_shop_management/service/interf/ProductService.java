package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductInfo(Integer productId);
    Boolean deleteProduct(Integer productId);
    ProductDto updateProduct(Integer productId, ProductDto productDto);
    ProductDto addProduct(ProductDto productDto);
    List<ProductDto> getProductsByCategory(Integer categoryId);
    List<ProductDto> searchProduct(String searchValue);
}
