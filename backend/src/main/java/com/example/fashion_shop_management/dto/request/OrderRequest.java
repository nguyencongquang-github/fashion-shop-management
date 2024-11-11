package com.example.fashion_shop_management.dto.request;

import com.example.fashion_shop_management.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    BigDecimal totalAmount;
    List<OrderItemRequest> orderItems;
    PaymentDto payment;
}
