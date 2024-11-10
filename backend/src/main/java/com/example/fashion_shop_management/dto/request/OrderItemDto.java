package com.example.fashion_shop_management.dto.request;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.example.fashion_shop_management.dto.user.UserDto;
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
public class OrderItemDto {
    Integer id;
    Integer quantity;
    BigDecimal price;
    ProductDto product;
    OrderDto order;
    UserDto user;
}
