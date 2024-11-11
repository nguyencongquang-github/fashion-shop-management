package com.example.fashion_shop_management.controller;

<<<<<<< HEAD
import com.example.fashion_shop_management.dto.request.*;
import com.example.fashion_shop_management.dto.response.AuthenticationResponse;
import com.example.fashion_shop_management.dto.response.IntrospectResponse;
import com.example.fashion_shop_management.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
=======
import com.example.fashion_shop_management.dto.response.ApiResponse;
import com.example.fashion_shop_management.dto.user.*;
import com.example.fashion_shop_management.service.impl.AuthServiceImpl;
import com.example.fashion_shop_management.service.JwtService;
import jakarta.persistence.PrePersist;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
<<<<<<< HEAD
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
=======
    JwtService jwtService;
    AuthServiceImpl authServiceImpl;

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO> register(@Valid @RequestBody RegisterUserDto registerUserDTO) {
        return ApiResponse.<UserResponseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .data(authServiceImpl.signup(registerUserDTO)).build();
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDTO) {
        return ApiResponse.<TokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Login successfully")
                .data(authServiceImpl.authenticate(loginUserDTO)).build();
    }

    @PostMapping("/verify")
    public ApiResponse<String> verify(@RequestBody VerifyUserDto verifyUserDTO) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(authServiceImpl.verifyUser(verifyUserDTO))
                .build();
    }

    @PostMapping("/resend")
    public ApiResponse<String> resend(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(authServiceImpl.resendVerificationCode(email))
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<TokenResponse> refreshToken(HttpServletRequest request) {
        return ApiResponse.<TokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Token refreshed successfully")
                .data(authServiceImpl.refreshAccessToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Logout successfully")
                .data(authServiceImpl.logout(request))
                .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(authServiceImpl.forgotPassword(email))
                .build();
    }

    @PutMapping("/reset-password")
    public ApiResponse<String> resetPassword(@RequestParam("reset-token") String resetToken, @RequestBody ResetPasswordDto request) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(authServiceImpl.resetPassword(resetToken, request))
                .build();
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    public ApiResponse<String> changePassword(@RequestBody ResetPasswordDto request, Principal connectedUser) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(authServiceImpl.changePassword(request, connectedUser))
                .build();
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
    }
}
