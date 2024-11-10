package com.example.fashion_shop_management.dto.coupon;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponCalculationResponse {
    Double result;
    String errorMessage;
}
