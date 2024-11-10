package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.comment.CommentRequestDto;
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.dto.comment.CommentResponseDto;
import com.example.fashion_shop_management.mapper.CommentMapper;
//import com.example.fashion_shop_management.mapper.CommentMapperImpl;
import com.example.fashion_shop_management.service.interf.CommentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/comment")
public class CommentController {
    CommentService commentService;
    CommentMapper commentMapper;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<CommentResponseDto>> getAllComments(
            @RequestParam(value = "user_id", required = false) Integer userId,
            @RequestParam(value = "product_id") Integer productId)
    {
       List<CommentResponseDto> comments;

       if (userId == null) {
           comments = commentService.getCommentsProduct(productId);
       } else {
           comments = commentService.getCommentsByUserAndProduct(userId, productId);
       }

         return ApiResponse.<List<CommentResponseDto>>builder()
                .code(200)
                .message("Get all comments successfully")
                .data(comments)
                .build();
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentDto) {
        return ApiResponse.<CommentResponseDto>builder()
                .code(200)
                .message("Create comment successfully")
                .data(commentService.addComment(commentDto))
                .build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CommentResponseDto> updateComment(@PathVariable("id") Integer commentId,
                                              @Valid @RequestBody CommentRequestDto request) {
        commentService.updateComment(commentId, request);
        return ApiResponse.<CommentResponseDto>builder()
                .code(200)
                .message("Update comment successfully")
                .data(null)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Boolean> deleteComment(
            @PathVariable("id") Integer commentId,
            @RequestParam("user_id") Integer userId) {
        return ApiResponse.<Boolean>builder()
                .code(200)
                .message("Delete comment successfully")
                .data(commentService.deleteComment(commentId, userId))
                .build();
    }

    @GetMapping("/average-rating")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Double> getAverageRating(@RequestParam("product_id") Integer productId) {
        return ApiResponse.<Double>builder()
                .code(200)
                .message("Get average rating successfully")
                .data(commentService.getAverageRating(productId))
                .build();
    }
}
