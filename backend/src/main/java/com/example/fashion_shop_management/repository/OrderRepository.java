package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.dto.request.OrderDto;
import com.example.fashion_shop_management.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser_Username(String username);

    Page<Order> findAll(Specification<Order> spec, Pageable pageable);
}
