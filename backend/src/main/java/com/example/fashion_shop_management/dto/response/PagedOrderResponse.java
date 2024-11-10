package com.example.fashion_shop_management.dto.response;

import com.example.fashion_shop_management.dto.request.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PagedOrderResponse {
    int totalPage;
    long totalElement;
    List<OrderDto> orderItems;
}
