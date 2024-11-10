package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.user.UserDto;
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserServiceImpl userServiceImpl;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping("/my-info")
    public ApiResponse<UserDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ApiResponse.<UserDto>builder()
                .code(HttpStatus.OK.value())
                .message("Get current user successfully")
                .data(userServiceImpl.getUserInfo(currentUser.getId()))
                .build();
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<List<UserDto>> getAllUsers() {
        return ApiResponse.<List<UserDto>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all users successfully")
                .data(userServiceImpl.getAllUsers())
                .build();
    }

    @GetMapping("/get-user/{userId}")
    public ApiResponse<UserDto> getUser(@PathVariable Integer userId) {
        return ApiResponse.<UserDto>builder()
                .code(HttpStatus.OK.value())
                .message("Get user successfully")
                .data(userServiceImpl.getUserInfo(userId))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Boolean> deleteUser(@PathVariable Integer userId) {
        userServiceImpl.deleteUser(userId);
        return ApiResponse.<Boolean>builder()
                .code(HttpStatus.OK.value())
                .message("Delete user successfully")
                .data(userServiceImpl.deleteUser(userId))
                .build();
    }

    @PutMapping("/update/{userId}")
    public ApiResponse<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        return ApiResponse.<UserDto>builder()
                .code(HttpStatus.OK.value())
                .message("Update user successfully")
                .data(userServiceImpl.updateUser(userId, userDto))
                .build();
    }

}
