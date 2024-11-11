package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.request.PaymentDto;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    PaymentDto.VNPayResponse createVnPayPayment(HttpServletRequest request);

    PaymentDto.VNPayDetailResponse handleVnPayCallback(HttpServletRequest request);
}
