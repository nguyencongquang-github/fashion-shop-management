//package com.example.fashion_shop_management.controller;
//
//import com.example.fashion_shop_management.dto.request.PaymentDto;
//import com.example.fashion_shop_management.dto.response.ApiResponse;
//import com.example.fashion_shop_management.service.interf.PaymentService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/payment")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class PaymentController {
//    PaymentService paymentService;
//
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
//    @GetMapping("/vn-pay")
//    public ApiResponse<PaymentDto.VNPayResponse> pay(HttpServletRequest request) {
//        return ApiResponse.<PaymentDto.VNPayResponse>builder()
//                .status(200)
//                .message("Success")
//                .result(paymentService.createVnPayPayment(request))
//                .build();
//    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
//    @GetMapping("/vn-pay-callback")
//    public ApiResponse<PaymentDto.VNPayDetailResponse> payCallbackHandler(HttpServletRequest request) {
//       PaymentDto.VNPayDetailResponse = paymentService.handleVnPayCallback(request);
//        return ApiResponse.<PaymentDto.VNPayDetailResponse>builder()
//                .status(200)
//                .message("Success")
//                .result(response)
//                .build();
//    }
//}
