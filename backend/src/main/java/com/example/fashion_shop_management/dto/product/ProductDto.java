package com.example.fashion_shop_management.dto.product;

import com.example.fashion_shop_management.dto.product_details.ProductDetailsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

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
    String color;
    MultipartFile image;
    BigDecimal importPrice;
    BigDecimal retailPrice;
    Integer categoryId;
    Integer promotionId;
    List<ProductDetailsDto> sizeDetails;
}
