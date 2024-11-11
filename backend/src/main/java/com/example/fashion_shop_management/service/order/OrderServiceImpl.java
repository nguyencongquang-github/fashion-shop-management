package com.example.fashion_shop_management.service.order;

import com.example.fashion_shop_management.constant.OrderStatus;
import com.example.fashion_shop_management.dto.request.OrderDto;
import com.example.fashion_shop_management.dto.response.PagedOrderResponse;
import com.example.fashion_shop_management.entity.Order;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.mapper.OrderMapper;
import com.example.fashion_shop_management.repository.OrderRepository;
import com.example.fashion_shop_management.specification.OrderSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Override
    public Page<OrderDto> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDto> orderDtoPage = orderRepository.findAll(pageable).map(orderMapper::toOrderDto);
        return orderDtoPage;
    }

    @Override
    public OrderDto getOrder(int orderId) {
        return orderRepository.findById(orderId).map(orderMapper::toOrderDto).orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public OrderDto createOrder(OrderDto order) {
        return orderMapper.toOrderDto(orderRepository.save(orderMapper.toOrder(order)));
    }

    @Override
    public OrderDto updateOrderStatus(int orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        return orderMapper.toOrderDto(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(int orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDto updateOrder(OrderDto order, int orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found");
        }
        return orderMapper.toOrderDto(orderRepository.save(orderMapper.toOrder(order)));
    }

    @Override
    public PagedOrderResponse filterOrders(String status, LocalDateTime startDate, LocalDateTime endDate, Integer userId, int page, int size) {
        OrderStatus orderStatus = status != null ? OrderStatus.valueOf(status.toUpperCase()) : null;
        Pageable pageable = PageRequest.of(page, size);

        Specification<Order> spec = Specification.where(OrderSpecification.hasStatus(orderStatus))
                .and(OrderSpecification.createDateBetween(startDate, endDate))
                .and(OrderSpecification.hasUserId(userId));

        Page<Order> orderPage = orderRepository.findAll(spec, pageable);

        if (orderPage.isEmpty()) {
            throw new NotFoundException("No orders found");
        }

        List<OrderDto> orderDtoList = orderPage.map(orderMapper::toOrderDto).toList();

        return PagedOrderResponse.builder()
                .orderItems(orderDtoList)
                .totalPage(orderPage.getTotalPages())
                .totalElement(orderPage.getTotalElements()).build();
    }

    @Override
    public List<OrderDto> getOrdersByUser(String username) {
        return orderRepository.findAllByUser_Username(username).stream().map(orderMapper::toOrderDto).toList();
    }
}
