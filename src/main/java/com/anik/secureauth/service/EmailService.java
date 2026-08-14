package com.anik.secureauth.service;

public interface EmailService {

    void sendVerificationEmail(
            String to,
            String name,
            String token
    );

    void sendPasswordResetEmail(String email, String token);
}