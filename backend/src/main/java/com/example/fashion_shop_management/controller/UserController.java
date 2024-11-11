package com.example.fashion_shop_management.controller;

<<<<<<< HEAD
import com.example.fashion_shop_management.dto.request.ApiResponse;
import com.example.fashion_shop_management.dto.request.UserCreationRequest;
import com.example.fashion_shop_management.dto.request.UserUpdateRequest;
import com.example.fashion_shop_management.dto.response.UserResponse;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build().getResult();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
=======
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

>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
}
