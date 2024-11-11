package com.example.fashion_shop_management.service.impl;


import com.example.fashion_shop_management.constant.PredefinedRole;
import com.example.fashion_shop_management.dto.user.*;
import com.example.fashion_shop_management.entity.Role;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.exception.InvalidDataException;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.mapper.UserMapper;
import com.example.fashion_shop_management.repository.RoleRepository;
import com.example.fashion_shop_management.repository.UserRepository;
import com.example.fashion_shop_management.service.EmailService;
import com.example.fashion_shop_management.service.JwtService;
import com.example.fashion_shop_management.service.interf.AuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    EmailService emailService;
    JwtService jwtService;
    UserDetailsService userDetailsService;
    UserMapper userMapper;
    private final UserServiceImpl userServiceImpl;


    public UserResponseDTO signup(RegisterUserDto request) {
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findByName(PredefinedRole.CUSTOMER_ROLE).ifPresent(roles::add);

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .verificationCode(generateVerificationCode())
                .verificationCodeExpired(LocalDateTime.now().plusMinutes(1))
                .roles(roles)
                .enabled(false)
                .build();

        sendVerificationEmail(user);
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    public TokenResponse authenticate(LoginUserDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please verify your account.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // Save token to database
        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshToken);

        userRepository.save(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    public String verifyUser(VerifyUserDto request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpired().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(request.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpired(null);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new NotFoundException("User not found");
        }

        return "Account verified successfully";
    }

    // Resend verification code
    public String resendVerificationCode(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isEnabled()) {
                throw new RuntimeException("Account is already verified");
            }

            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpired(LocalDateTime.now().plusMinutes(1));
            userRepository.save(user);

            sendVerificationEmail(user);
        } else {
            throw new NotFoundException("User not found");
        }

        return "Verification code sent successfully";
    }

    public String logout(HttpServletRequest request) {
        String refreshToken = request.getHeader("x-token");
        if (refreshToken == null) {
            throw new RuntimeException("Token must be not blank");
        }

        // Extract username from token
        final String username = jwtService.extractUsername(refreshToken);
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optionalUser.get();
        user.setAccessToken(null);
        userRepository.save(user);

        return "Logout successfully";
    }

    private String sendVerificationEmail(User user) {
        String subject = "ACCOUNT VERIFICATION";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }

        return "Verification code sent successfully";
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    // Refresh access token when it's expired
    public TokenResponse refreshAccessToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("x-token");
        if (refreshToken == null) {
            throw new RuntimeException("Token must be not blank");
        }
        final String username = jwtService.extractUsername(refreshToken);
        Optional<User> user = userRepository.findByUsername(username);

        if (!jwtService.isTokenValid(refreshToken, user.get())) {
            throw new RuntimeException("Token is invalid");
        }

        String accessToken = jwtService.generateToken(user.get());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.get().getId())
                .build();
    }

    public String forgotPassword(String email) {
        // Check email exists or not
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optionalUser.get();

        // User is active or inactive
        if (!user.isEnabled()) {
            throw new InvalidDataException("User is not active");
        }

        // Generate reset token
        String resetToken = jwtService.generateResetPasswordToken(user);

        // Send email reset link
        String subject = "RESET PASSWORD";
        String resetLink = "http://localhost:8080/auth/reset-password?reset-token=" + resetToken;
        String htmlMessage = "<html>"
                + "<body>"
                + "<p>Dear " + user.getUsername() + ",</p>"
                + "<p>We received a request to reset your password. Click the link below to set a new password:</p>"
                + "<a href=\"" + resetLink + "\">Reset Password</a>"
                + "<p>If you didn't request a password reset, please ignore this email.</p>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send reset password email", e);
        }

        return "Reset password link sent successfully";
    }

    @Transactional
    public String resetPassword(String resetToken, ResetPasswordDto request) {
        log.info("========== Reset password ==========");

        // Check token and return user
        User user = isValidUserByToken(resetToken);

        // Check new password and confirm password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password do not match");
        }

        // Set new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        Set<Role> roles = user.getRoles();
        user.setRoles(roles);
        userRepository.save(user);

        return "Password reset successfully";
    }

    // Change password with token
    public String changePassword(ResetPasswordDto request, Principal connectedUser) {
        log.info("========= Change password ==========");

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // Check current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidDataException("Invalid old password");
        }

        // Check new password and confirm password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new InvalidDataException("Password and confirm password do not match");
        }

        // Set new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return "Password changed successfully";
    }

    // Check token and return user
    private User isValidUserByToken(String token) {
        final String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (!jwtService.isTokenValid(token, user)) {
            throw new RuntimeException("Token is invalid");
        }

        return user;
    }
}