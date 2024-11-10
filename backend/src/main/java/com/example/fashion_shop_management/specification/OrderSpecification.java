package com.example.fashion_shop_management.specification;

import com.example.fashion_shop_management.constant.OrderStatus;
import com.example.fashion_shop_management.entity.Order;
import com.example.fashion_shop_management.entity.OrderItem;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecification {

    // Specification to filter order by status
    public static Specification<Order> hasStatus(OrderStatus status) {
        return ((root, query, criteriaBuilder) -> status != null ? criteriaBuilder.equal(root.get("status"), status) : null);
    }

    // Specification to filter order by data range
    public static Specification<Order> createDateBetween(LocalDateTime start, LocalDateTime end) {
        return ((root, query, criteriaBuilder) -> {
            if (start != null && end != null) {
                return criteriaBuilder.between(root.get("createDate"), start, end);
            } else if (start != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), start);
            } else if (end != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), end);
            } else {
                return null;
            }
        });
    }

    // Specification to filter order by product id
    public static Specification<Order> hasProductId(Integer productId) {
        return ((root, query, criteriaBuilder) -> productId != null ? criteriaBuilder.equal(root.get("product").get("id"), productId) : null);
    }

    // Specification to filter order by user id
    public static Specification<Order> hasUserId(Integer userId) {
        return ((root, query, criteriaBuilder) -> userId != null ? criteriaBuilder.equal(root.get("user").get("id"), userId) : null);
    }


}
