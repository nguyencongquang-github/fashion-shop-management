package com.example.fashion_shop_management.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
//    Integer id;
//    BigDecimal amount;
//    String method;
//    String status;
//    OrderDto order;'

    @Builder
    public static class VNPayResponse {
        public String paymentUrl;
    }

    @Builder
    public static class VNPayDetailResponse {
        private String orderId;
        private long amount;
        private String paymentDate;
        private String status;
        private String bankCode;
        private String orderInfo;
        private String orderType;
        private String transactionId;
    }
}
