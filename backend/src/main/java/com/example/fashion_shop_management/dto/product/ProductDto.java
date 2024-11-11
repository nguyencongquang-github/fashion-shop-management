package com.example.fashion_shop_management.dto.product;

import com.example.fashion_shop_management.dto.category.CategoryDto;
import com.example.fashion_shop_management.dto.request.PromotionDto;
import com.example.fashion_shop_management.entity.Category;
import com.example.fashion_shop_management.entity.Promotion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Integer id;
    String name;
    String description;
    String imageUrl;
    String color;
    String size;
    BigDecimal importPrice;
    BigDecimal retailPrice;
    int stock;
    CategoryDto category;
    PromotionDto promotion;
}
