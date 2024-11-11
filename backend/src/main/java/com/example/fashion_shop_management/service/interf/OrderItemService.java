package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.response.PagedOrderResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface OrderItemService {

    void updateOrderItemStatus(int orderItemId, String status);
    PagedOrderResponse filterOrderItems(LocalDateTime startDate, LocalDateTime endDate, Integer itemId, Pageable pageable);

}
