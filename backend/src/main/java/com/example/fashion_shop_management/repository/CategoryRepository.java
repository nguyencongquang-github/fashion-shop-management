package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
