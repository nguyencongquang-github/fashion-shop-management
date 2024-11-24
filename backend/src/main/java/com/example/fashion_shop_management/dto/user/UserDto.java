package com.example.fashion_shop_management.dto.user;

import com.example.fashion_shop_management.dto.request.OrderDto;
import com.example.fashion_shop_management.dto.request.OrderItemDto;
import com.example.fashion_shop_management.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Integer id;
    String username;
    String email;
    String gender;
    String phone;
    String imageUrl;
    String address;
    boolean enabled;
    boolean accountLocked;
    LocalDate dateOfBirth;
    String verificationCode;
    LocalDateTime verificationCodeExpired;
    String accessToken;
    String refreshToken;
    Set<Role> roles;
    List<OrderDto> orderItems;
}
