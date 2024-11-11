package com.example.fashion_shop_management.dto.request;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionDto {
    Integer id;
    String name;
    String description;
    String type;
    String status;
    int usageLimit;
    BigDecimal discount;
    BigDecimal minOrder;
    BigDecimal maxDiscount;
    LocalDateTime startDate;
    LocalDateTime endDate;
    List<ProductDto> products;
    List<OrderDto> orders;
}
