package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.comment.CommentResponseDto;
import com.example.fashion_shop_management.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CommentMapper extends EntityMapper<CommentResponseDto, Comment> {

}
