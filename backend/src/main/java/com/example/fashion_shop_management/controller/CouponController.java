package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.coupon.CouponCalculationResponse;
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.service.coupon.CouponService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CouponController {
    CouponService couponService;

    @GetMapping("/calculate")
    public ApiResponse<CouponCalculationResponse> calculateCouponValue(
            @RequestParam("couponCode") String couponCode,
            @RequestParam("totalAmount") double totalAmount)
    {
        try {
            double finalAmount = couponService.calculateCouponValue(couponCode, totalAmount);
            CouponCalculationResponse response = new CouponCalculationResponse();
            response.setResult(finalAmount);
            return ApiResponse.<CouponCalculationResponse>builder()
                    .code(HttpStatus.OK.value())
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<CouponCalculationResponse>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }


    }
}
