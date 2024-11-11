package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategoryId(int categoryId);
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}
