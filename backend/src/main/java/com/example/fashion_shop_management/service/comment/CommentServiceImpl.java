package com.example.fashion_shop_management.service.comment;

import com.example.fashion_shop_management.dto.comment.CommentRequestDto;
import com.example.fashion_shop_management.dto.comment.CommentResponseDto;
import com.example.fashion_shop_management.entity.Comment;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.exception.UnauthorizedAccessException;
import com.example.fashion_shop_management.mapper.CommentMapper;
import com.example.fashion_shop_management.repository.CommentRepository;
import com.example.fashion_shop_management.repository.ProductRepository;
import com.example.fashion_shop_management.repository.UserRepository;
import com.example.fashion_shop_management.service.comment.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;
    UserRepository userRepository;
    ProductRepository productRepository;


//    @Override
//    @Transactional
//    public CommentResponseDto addComment(CommentDto commentDto) {
//        Comment comment = commentMapper.toEntity(commentDto);
//        return commentMapper.toResponseDto(commentRepository.save(comment));
//    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (!request.getUserId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You can't comment for other user");
        }

        Comment comment = Comment.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .user(userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("User not found")))
                .product(productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Product not found")))
                .build();

        return commentMapper.toDto(commentRepository.save(comment));
    }


    @Override
    @Transactional
    public Boolean updateComment(Integer commentId, Integer userId, CommentRequestDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (!userId.equals(user.getId())) {
            throw new UnauthorizedAccessException("You can't edit comment for other user");
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.setRating(request.getRating());
        comment.setContent(request.getContent());
        commentRepository.save(comment);

        return true;
    }

    @Override
    public Boolean deleteComment(Integer commentId, Integer userId) {
        // Get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Check user delete comment of other user
        if (!userId.equals(user.getId())) {
            throw new UnauthorizedAccessException("You can't delete comment for other user");
        }

        // Check comment exists or not
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        // Check user createProductSize this comment or not
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You can only delete your own comments");
        }

        commentRepository.deleteById(commentId);
        commentRepository.flush();

        return !commentRepository.existsById(commentId);
    }



    @Override
    public List<CommentResponseDto> getCommentsProduct(Integer productId) {
        return commentMapper.toDtos(commentRepository.findByProductId(productId));
    }

    @Override
    public double getAverageRating(Integer productId) {
        List<Comment> comments = commentRepository.findByProductId(productId);
        if (comments.isEmpty()) {
            return 0;
        }
        double averageRating = comments.stream().mapToDouble(Comment::getRating).average().orElse(0);
        return Math.round(averageRating * 10) / 10.0;
    }


    @Override
    public List<CommentResponseDto> getCommentsByUserAndProduct(Integer userId, Integer productId) {
        return commentMapper.toDtos(commentRepository.findByUserIdAndProductId(userId, productId));
    }
}
