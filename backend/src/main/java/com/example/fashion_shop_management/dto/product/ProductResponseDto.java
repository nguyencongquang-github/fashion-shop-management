package com.example.fashion_shop_management.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseDto {
    Integer id;
    String name;
    String description;
    String imageUrl;
    String color;
    String size;
    BigDecimal importPrice;
    BigDecimal retailPrice;
    int stock;
//
//    CategoryResponseDto category;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "promotion_id")
//    Promotion promotion;
//
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<Comment> comments = new ArrayList<>();
//
//    @CreatedDate
//    @Column(name = "create_date", nullable = false)
//    LocalDateTime createDate;
//    @LastModifiedDate
//    @Column(name = "update_date", insertable = false)
//    LocalDateTime updateDate;
}
