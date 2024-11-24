package com.example.fashion_shop_management.service.order;

import com.example.fashion_shop_management.constant.OrderStatus;
import com.example.fashion_shop_management.dto.request.OrderDto;
import com.example.fashion_shop_management.dto.response.PagedOrderResponse;
import com.example.fashion_shop_management.entity.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Page<OrderDto> getOrders(int page, int size);
    OrderDto getOrder(int orderId);
    OrderDto createOrder(OrderDto order);
    OrderDto updateOrderStatus(int orderId, OrderStatus status);
    void deleteOrder(int orderId);
    OrderDto updateOrder(OrderDto order, int orderId);
    PagedOrderResponse filterOrders(String status, LocalDateTime startDate, LocalDateTime endDate, Integer orderId, int page, int size);
    List<OrderDto> getOrdersByUser(String username);
    OrderDto placeOrder(OrderDto orderDto);

    Order checkout(Integer userId, String paymentMethod);
}
