package com.example.fashion_shop_management.service.other.email;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendVerificationEmail(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException;
}
