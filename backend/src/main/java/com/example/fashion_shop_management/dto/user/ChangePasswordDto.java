package com.example.fashion_shop_management.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    private String currentPassword;
    private String newPassword;
}
