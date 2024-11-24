package com.example.fashion_shop_management.controller;

import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.dto.user.*;
import com.example.fashion_shop_management.service.auth.AuthServiceImpl;
import com.example.fashion_shop_management.service.other.jwt.JwtServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    JwtServiceImpl jwtServiceImpl;
    AuthServiceImpl authServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterUserDto registerUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authServiceImpl.signup(registerUserDTO));
    }
//    public ApiResponse<UserResponseDTO> register(@Valid @RequestBody RegisterUserDto registerUserDTO) {
//        return ApiResponse.<UserResponseDTO>builder()
//                .code(HttpStatus.CREATED.value())
//                .message("User registered successfully")
//                .data(authServiceImpl.signup(registerUserDTO)).build();
//    }

    @PostMapping("/login")
//    public ApiResponse<TokenResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDTO) {
//        return ApiResponse.<TokenResponse>builder()
//                .code(HttpStatus.OK.value())
//                .message("Login successfully")
//                .data(authServiceImpl.authenticate(loginUserDTO)).build();
//    }
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDTO) {
        return ResponseEntity.ok(authServiceImpl.authenticate(loginUserDTO));
    }

    @PostMapping("/verify")
//    public ApiResponse<String> verify(@RequestBody VerifyUserDto verifyUserDTO) {
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message(authServiceImpl.verifyUser(verifyUserDTO))
//                .build();
//    }
    public ResponseEntity<String> verify(@RequestBody VerifyUserDto verifyUserDTO) {
        return ResponseEntity.ok(authServiceImpl.verifyUser(verifyUserDTO));
    }

    @PostMapping("/resend")
//    public ApiResponse<String> resend(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message(authServiceImpl.resendVerificationCode(email))
//                .build();
//    }
    public ResponseEntity<String> resend(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ResponseEntity.ok(authServiceImpl.resendVerificationCode(email));
    }

    @PostMapping("/refresh-token")
//    public ApiResponse<TokenResponse> refreshToken(HttpServletRequest request) {
//        return ApiResponse.<TokenResponse>builder()
//                .code(HttpStatus.OK.value())
//                .message("Token refreshed successfully")
//                .data(authServiceImpl.refreshAccessToken(request))
//                .build();
//    }
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(authServiceImpl.refreshAccessToken(request));
    }

    @PostMapping("/logout")
//    public ApiResponse<String> logout(HttpServletRequest request) {
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message("Logout successfully")
//                .data(authServiceImpl.logout(request))
//                .build();
//    }
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authServiceImpl.logout(request));
    }

    @PostMapping("/forgot-password")
//    public ApiResponse<String> forgotPassword(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message(authServiceImpl.forgotPassword(email))
//                .build();
//    }
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ResponseEntity.ok(authServiceImpl.forgotPassword(email));
    }

    @PutMapping("/reset-password")
//    public ApiResponse<String> resetPassword(@RequestParam("reset-token") String resetToken, @RequestBody ResetPasswordDto request) {
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message(authServiceImpl.resetPassword(resetToken, request))
//                .build();
//    }
    public ResponseEntity<String> resetPassword(@RequestParam("reset-token") String resetToken, @RequestBody ResetPasswordDto request) {
        return ResponseEntity.ok(authServiceImpl.resetPassword(resetToken, request));
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
//    public ApiResponse<String> changePassword(@RequestBody ResetPasswordDto request, Principal connectedUser) {
//        return ApiResponse.<String>builder()
//                .code(HttpStatus.OK.value())
//                .message(authServiceImpl.changePassword(request, connectedUser))
//                .build();
//    }
    public ResponseEntity<String> changePassword(@RequestBody ResetPasswordDto request, Principal connectedUser) {
        return ResponseEntity.ok(authServiceImpl.changePassword(request, connectedUser));
    }


}
