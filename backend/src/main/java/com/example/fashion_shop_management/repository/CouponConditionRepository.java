package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.CouponCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponConditionRepository extends JpaRepository<CouponCondition, Integer> {
    List<CouponCondition> findByCouponId(Integer couponId);
}
