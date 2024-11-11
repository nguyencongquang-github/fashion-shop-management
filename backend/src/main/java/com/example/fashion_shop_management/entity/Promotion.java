package com.example.fashion_shop_management.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;
    String type;
    String status;
    int usageLimit;
    BigDecimal discount;
    BigDecimal minOrder;
    BigDecimal maxDiscount;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    List<Product> products;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    List<Order> orders;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    LocalDateTime createDate;
    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    LocalDateTime updateDate;
}
