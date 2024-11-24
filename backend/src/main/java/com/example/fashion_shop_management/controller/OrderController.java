//package com.example.fashion_shop_management.controller;
//
//import com.example.fashion_shop_management.dto.request.OrderDto;
//import com.example.fashion_shop_management.dto.response.ApiResponse;
//import com.example.fashion_shop_management.service.order.OrderService;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/orders")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class OrderController {
//    OrderService orderService;
//
//    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ApiResponse<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
//        return ApiResponse.<OrderDto>builder()
//                .code(200)
//                .message("Create order successfully")
//                .data(orderService.createOrder(orderDto))
//                .build();
//    }
//
//    @PostMapping("/update/{orderId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ApiResponse<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable Integer orderId) {
//        return ApiResponse.<OrderDto>builder()
//                .code(200)
//                .message("Update order successfully")
//                .data(orderService.updateOrder(orderDto, orderId))
//                .build();
//    }
//
//    @DeleteMapping("/delete/{orderId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ApiResponse<Boolean> deleteOrder(@PathVariable Integer orderId) {
//        orderService.deleteOrder(orderId);
//        return ApiResponse.<Boolean>builder()
//                .code(200)
//                .message("Delete order successfully")
//                .data(true)
//                .build();
//    }
//
//    @GetMapping("/get-all")
//    public ApiResponse<List<OrderDto>> getAllOrders(@RequestParam int page, @RequestParam int size) {
//        return ApiResponse.<List<OrderDto>>builder()
//                .code(200)
//                .message("Get all orders successfully")
//                .data(orderService.getOrders(page, size).getContent())
//                .build();
//    }
//
//    @GetMapping("/get-order/{orderId}")
//    public ApiResponse<OrderDto> getOrder(@PathVariable Integer orderId) {
//        return ApiResponse.<OrderDto>builder()
//                .code(200)
//                .message("Get order successfully")
//                .data(orderService.getOrder(orderId))
//                .build();
//    }
//
//    @GetMapping("/get-orders-by-user/{username}")
//    public ApiResponse<List<OrderDto>> getOrdersByUser(@PathVariable String username) {
//        return ApiResponse.<List<OrderDto>>builder()
//                .code(200)
//                .message("Get orders by user successfully")
//                .data(orderService.getOrdersByUser(username))
//                .build();
//    }
//
//    @GetMapping("/filter")
//    public ApiResponse<List<OrderDto>> filterOrders(@RequestParam String status,
//                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
//                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
//                                                    @RequestParam Integer userId,
//                                                    @RequestParam int page,
//                                                    @RequestParam int size) {
//        return ApiResponse.<List<OrderDto>>builder()
//                .code(200)
//                .message("Filter orders successfully")
//                .data(orderService.filterOrders(status, startDate, endDate, userId, page, size).getOrderItems())
//                .build();
//    }
//
//    @PostMapping("/place-order")
//    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
//        return ResponseEntity.ok(orderService.placeOrder(orderDto));
//    }
//}
