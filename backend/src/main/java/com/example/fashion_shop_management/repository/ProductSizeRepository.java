package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.constant.Size;
import com.example.fashion_shop_management.entity.Product;
import com.example.fashion_shop_management.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    void deleteAllByProductId(Integer productId);
    Optional<ProductSize> findBySize(Size size);
    List<ProductSize> findByProductId(Integer productId);
    Optional<ProductSize> findByProductAndSize(Product product, Size size);
}
