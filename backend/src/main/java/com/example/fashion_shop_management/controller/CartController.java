//package com.example.fashion_shop_management.controller;
//
//import com.example.fashion_shop_management.entity.Order;
//import com.example.fashion_shop_management.entity.ShoppingCart;
//import com.example.fashion_shop_management.service.CartService;
//import com.example.fashion_shop_management.service.order.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/carts")
//public class CartController {
//    private final CartService cartService;
//    private final OrderService orderService;
//
//    // TODO: View cart
//    @GetMapping("/view")
//    public ResponseEntity<ShoppingCart> viewCart(@RequestParam Integer userId) {
//        return ResponseEntity.ok(cartService.viewCart(userId));
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<ShoppingCart> createCart(@RequestParam Integer userId,
//                                                   @RequestParam Integer productId,
//                                                   @RequestParam String size,
//                                                   @RequestParam Integer quantity) {
//        return ResponseEntity.ok(cartService.addToCart(userId, productId, size, quantity));
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<ShoppingCart> updateCart(@RequestParam Integer userId,
//                                                   @RequestParam Integer productId,
//                                                   @RequestParam String size,
//                                                   @RequestParam Integer quantity) {
//        return ResponseEntity.ok(cartService.updateCart(userId, productId, size, quantity));
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<ShoppingCart> deleteCart(@RequestParam Integer userId,
//                                                   @RequestParam Integer productId) {
//        return ResponseEntity.ok(cartService.deleteCart(userId, productId));
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<Order> checkout(@RequestParam Integer userId,
//                                          @RequestParam String paymentMethod) {
//        return ResponseEntity.ok(orderService.checkout(userId, paymentMethod));
//    }
//}
