package com.example.fashion_shop_management.service.impl;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.example.fashion_shop_management.entity.Product;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.mapper.ProductMapper;
import com.example.fashion_shop_management.repository.ProductRepository;
import com.example.fashion_shop_management.service.interf.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductDto).toList();
    }

    @Override
    public ProductDto getProductInfo(Integer productId) {
        return productRepository.findById(productId).map(productMapper::toProductDto).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Boolean deleteProduct(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product not found");
        }
        productRepository.deleteById(productId);
        return !productRepository.existsById(productId);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product not found");
        }
        Product product = productRepository.findById(productId).get();
        Product updatedProduct = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productRepository.save(productMapper.toProduct(productDto)));
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productMapper.toProductDto(productRepository.save(productMapper.toProduct(productDto)));
    }

    @Override
    public List<ProductDto> getProductsByCategory(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(productMapper::toProductDto).toList();
    }

    @Override
    public List<ProductDto> searchProduct(String searchValue) {
        List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(searchValue, searchValue);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }

        return products.stream().map(productMapper::toProductDto).toList();
    }
}
