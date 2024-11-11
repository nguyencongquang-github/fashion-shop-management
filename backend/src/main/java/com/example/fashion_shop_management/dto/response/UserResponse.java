package com.example.fashion_shop_management.dto.response;

import com.example.fashion_shop_management.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullname;
    String email;
    LocalDate dob;
    Set<RoleResponse> roles;
}
