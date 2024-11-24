package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategoryId(int categoryId);
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
    @EntityGraph(attributePaths = {"productSizes"})
    @Query("SELECT p FROM Product p")
    Page<Product> findAllWithSizes(Pageable pageable);
}
