//package com.example.fashion_shop_management.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import software.amazon.awssdk.services.s3.endpoints.internal.Value;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "order_items")
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@EntityListeners(AuditingEntityListener.class)
//public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer id;
//
//    String size;
//
//    @Min(1)
//    Integer quantity;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shopping_cart_id")
//    @JsonBackReference
//    ShoppingCart shoppingCart;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    Product product;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "coupon_id")
//    @JsonBackReference
//    Coupon coupon;
//
//    @CreatedDate
//    @Column(name = "create_date", nullable = false)
//    LocalDateTime createDate;
//    @LastModifiedDate
//    @Column(name = "update_date", insertable = false)
//    LocalDateTime updateDate;
//
//
//    public BigDecimal getTotalPrice() {
//        return product.getRetailPrice().multiply(BigDecimal.valueOf(quantity));
//    }
//}
