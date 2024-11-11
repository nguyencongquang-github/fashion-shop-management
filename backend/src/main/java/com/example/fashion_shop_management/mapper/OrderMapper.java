package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.request.OrderDto;
import com.example.fashion_shop_management.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto orderDto);
}
