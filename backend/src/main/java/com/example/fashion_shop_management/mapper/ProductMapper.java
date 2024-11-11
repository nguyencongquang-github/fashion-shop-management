package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.example.fashion_shop_management.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
