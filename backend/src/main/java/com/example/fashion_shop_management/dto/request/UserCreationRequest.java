package com.example.fashion_shop_management.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;
    String fullname;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String email;
    Date createDate;
}
