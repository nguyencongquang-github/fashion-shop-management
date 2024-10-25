package com.example.fashion_shop_management.dto.request;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    String fullname;
    String password;
    Date dob;
    List<String> roles;
}
