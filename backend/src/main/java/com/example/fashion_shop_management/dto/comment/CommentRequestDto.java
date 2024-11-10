package com.example.fashion_shop_management.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDto {
    private int rating;
    private String content;
    private Integer productId;
    private Integer userId;
}
