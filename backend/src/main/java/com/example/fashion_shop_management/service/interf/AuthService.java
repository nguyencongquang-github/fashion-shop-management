package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.user.*;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

public interface AuthService {
    UserResponseDTO signup(RegisterUserDto input);
    TokenResponse authenticate(LoginUserDto input);
    String verifyUser(VerifyUserDto input);
    String resendVerificationCode(String email);
    String logout(HttpServletRequest request);
    TokenResponse refreshAccessToken(HttpServletRequest request);
    String forgotPassword(String email);
    String resetPassword(String resetToken, ResetPasswordDto request);
    String changePassword(ResetPasswordDto request, Principal connectedUser);
}
