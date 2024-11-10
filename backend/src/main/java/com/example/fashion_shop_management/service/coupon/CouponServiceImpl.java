package com.example.fashion_shop_management.service.coupon;

import com.example.fashion_shop_management.entity.Coupon;
import com.example.fashion_shop_management.entity.CouponCondition;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.repository.CouponConditionRepository;
import com.example.fashion_shop_management.repository.CouponRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CouponServiceImpl implements CouponService {

    CouponRepository couponRepository;
    CouponConditionRepository couponConditionRepository;

    @Override
    public double calculateCouponValue(String couponCod, double totalAmount) {
        Coupon  coupon = couponRepository.findByCode(couponCod).orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        if (!coupon.isActive()) {
            throw new IllegalArgumentException("Coupon is not active");
        }

        double discount = calculateDiscount(coupon, totalAmount);
        return totalAmount - discount;
    }

    private double calculateDiscount(Coupon coupon, double totalAmount) {
        List<CouponCondition> couponConditions = couponConditionRepository.findByCouponId(coupon.getId());
        double discount = 0.0;
        double updatedTotalAmount = totalAmount;

        for (CouponCondition couponCondition : couponConditions) {
            String attribute = couponCondition.getAttribute();
            String operator = couponCondition.getOperator();
            String value = couponCondition.getValue();
            double percentAmount = Double.valueOf(String.valueOf(couponCondition.getDiscountAmount()));

            if (attribute.equals("minimum_amount")) {
                if (operator.equals(">") && updatedTotalAmount > Double.valueOf(value)) {
                    discount += updatedTotalAmount * percentAmount / 100;
                }
            } else if (attribute.equals("applicable_date")) {
                LocalDate applicableDate = LocalDate.parse(value);
                if (applicableDate.isBefore(LocalDate.now())) {
                    discount += updatedTotalAmount * percentAmount / 100;
                }
            }

            // Add more conditions here
            updatedTotalAmount -= discount;
        }

        return  discount;
    }
}
