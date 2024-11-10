package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.comment.CommentRequestDto;
import com.example.fashion_shop_management.dto.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto request);
    Boolean deleteComment(Integer commentId, Integer userId);
    Boolean updateComment(Integer commentId, CommentRequestDto request);
    List<CommentResponseDto> getCommentsProduct(Integer productId);
    double getAverageRating(Integer productId);
    List<CommentResponseDto> getCommentsByUserAndProduct(Integer userId, Integer productId);
}
