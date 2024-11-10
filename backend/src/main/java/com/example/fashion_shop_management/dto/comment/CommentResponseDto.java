package com.example.fashion_shop_management.dto.comment;

import com.example.fashion_shop_management.dto.product.ProductDto;
import com.example.fashion_shop_management.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private Integer id;
    private int rating;
    private String content;

    private ProductDto product;
    private UserDto user;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
