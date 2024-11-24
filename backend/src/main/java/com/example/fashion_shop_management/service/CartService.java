//package com.example.fashion_shop_management.service;
//
//import com.example.fashion_shop_management.constant.Size;
//import com.example.fashion_shop_management.entity.*;
//import com.example.fashion_shop_management.exception.NotFoundException;
//import com.example.fashion_shop_management.repository.ProductRepository;
//import com.example.fashion_shop_management.repository.ProductSizeRepository;
//import com.example.fashion_shop_management.repository.ShoppingCartRepository;
//import com.example.fashion_shop_management.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class CartService {
//    private final ShoppingCartRepository shoppingCartRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final ProductSizeRepository ProductSizeRepository;
//
//    public ShoppingCart viewCart(Integer userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//        return shoppingCartRepository.findByUserId(userId);
//    }
//
//    public ShoppingCart addToCart(Integer userId, Integer productId, String size, Integer quantity) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("Product not found"));
//
//        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
//
//        Size s = Size.valueOf(size);
//
//        ProductSize productSize = ProductSizeRepository.findByProductAndSize(product, s)
//                .orElseThrow(() -> new NotFoundException("Product size not found"));
//
//       // Check product exists in cart
//        Optional<CartItem> existingItem = shoppingCart.getCartItems().stream()
//                .filter(item -> item.getProduct().getId().equals(productId)  && item.getSize().equals(size))
//                .findFirst();
//
//        if (productSize.getStock() >= quantity) {
//            if (existingItem.isPresent()) {
//                CartItem item = existingItem.get();
//                item.setQuantity(item.getQuantity() + quantity);
//            } else {
//                CartItem item = new CartItem();
//                item.setProduct(product);
//                item.setQuantity(quantity);
//                item.setSize(productSize.getSize().toString());
//                shoppingCart.getCartItems().add(item);
//            }
//            return shoppingCartRepository.save(shoppingCart);
//        } else {
//            throw new NotFoundException("Product out of stock");
//        }
//    }
//
//    public ShoppingCart updateCart(Integer userId, Integer productId, String size, Integer quantity) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("Product not found"));
//
//        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
//
//        Size s = Size.valueOf(size);
//
//        ProductSize productSize = ProductSizeRepository.findByProductAndSize(product, s)
//                .orElseThrow(() -> new NotFoundException("Product size not found"));
//
//        CartItem item = shoppingCart.getCartItems().stream()
//                .filter(i -> i.getProduct().getId().equals(productId) && i.getSize().equals(size))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Product not found in cart"));
//
//        if (productSize.getStock() >= quantity) {
//            item.setQuantity(quantity);
//            return shoppingCartRepository.save(shoppingCart);
//        } else {
//            throw new NotFoundException("Product out of stock");
//        }
//    }
//
//    public ShoppingCart deleteCart(Integer userId, Integer productId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
//
//        CartItem item = shoppingCart.getCartItems().stream()
//                .filter(i -> i.getProduct().getId().equals(productId))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Product not found in cart"));
//
//        shoppingCart.getCartItems().remove(item);
//        return shoppingCartRepository.save(shoppingCart);
//    }
//}
