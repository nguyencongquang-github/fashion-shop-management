//package com.example.fashion_shop_management.service.impl;
//
//import com.example.fashion_shop_management.config.payment.VNPAYConfig;
//import com.example.fashion_shop_management.dto.request.PaymentDto;
//import com.example.fashion_shop_management.service.interf.PaymentService;
//import com.example.fashion_shop_management.util.VNPayUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.security.MessageDigest;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class PaymentServiceImpl implements PaymentService {
//    VNPAYConfig vnpayConfig;
//
//    @Override
//    public PaymentDto.VNPayResponse createVnPayPayment(HttpServletRequest request) {
//        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
//        String bankCode = request.getParameter("bankCode");
//        Map<String, String> vnParamsMap = vnpayConfig.getVNPayConfig();
//        vnParamsMap.put("vnp_Amount", String.valueOf(amount));
//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnParamsMap.put("vnp_BankCode", bankCode);
//        }
//        vnParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
//
//        // Build query url
//        String queryUrl = VNPayUtil.getPaymentURL(vnParamsMap, true);
//        String hashData = VNPayUtil.getPaymentURL(vnParamsMap, false);
//        String secureHash = VNPayUtil.hmacSHA512(vnpayConfig.getSecretKey() , hashData);
//        queryUrl += "&vnp_SecureHash=" + secureHash;
//        String paymentUrl = vnpayConfig.getVnp_PayUrl() + "?" + queryUrl;
//
//        return PaymentDto.VNPayResponse.builder()
//                .paymentUrl(paymentUrl)
//                .build();
//    }
//
//    @Override
//    public PaymentDto.VNPayDetailResponse handleVnPayCallback(HttpServletRequest request) {
//        Map<String, String> vnpParams = VNPayUtil.getParamsFromRequest(request);
//        try {
//            if (verifySecureHash(vnpParams, vnpayConfig.getSecretKey())) {
//                return PaymentDto.VNPayDetailResponse.builder()
//                        .orderId(vnpParams.get("vnp_TxnRef"))
//                        .amount(Long.parseLong(vnpParams.get("vnp_Amount")))
//                        .paymentDate(vnpParams.get("vnp_PayDate"))
//                        .status(vnpParams.get("vnp_ResponseCode"))
//                        .bankCode(vnpParams.get("vnp_BankCode"))
//                        .orderInfo(vnpParams.get("vnp_OrderInfo"))
//                        .orderType(vnpParams.get("vnp_OrderType"))
//                        .transactionId(vnpParams.get("vnp_TransactionNo"))
//                        .build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//    }
//
//
//    public static boolean verifySecureHash(Map<String, String> vnpParams, String secretKey) throws Exception {
//        // Lấy mã SecureHash từ callback URL
//        String secureHash = vnpParams.get("vnp_SecureHash");
//        vnpParams.remove("vnp_SecureHash");
//
//        // Sắp xếp các tham số theo thứ tự abc
//        SortedMap<String, String> sortedParams = new TreeMap<>(vnpParams);
//        StringBuilder hashData = new StringBuilder();
//        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
//            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
//                hashData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//            }
//        }
//        hashData.deleteCharAt(hashData.length() - 1); // Xóa dấu '&' cuối cùng
//
//        // Tạo mã băm sử dụng secretKey
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        byte[] hashBytes = md.digest((secretKey + hashData.toString()).getBytes("UTF-8"));
//        StringBuilder hashString = new StringBuilder();
//        for (byte b : hashBytes) {
//            hashString.append(String.format("%02x", b));
//        }
//
//        // So sánh mã băm
//        return secureHash.equals(hashString.toString());
//    }
//
//
//
//}
