package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByUserIdAndProductId(Integer userId, Integer productId);

    List<Comment> findByProductId(Integer productId);
}
