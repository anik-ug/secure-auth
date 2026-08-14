package com.anik.secureauth.service.impl;

import com.anik.secureauth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Override
    public void sendVerificationEmail(
            String to,
            String name,
            String token
    ) {

        String verificationLink =
                frontendUrl + "/verify-email?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Verify Your Email");

        message.setText(
                "Hello " + name + ",\n\n" +
                "Please verify your email by clicking the link below:\n\n" +
                verificationLink +
                "\n\nThis link will expire in 24 hours."
        );

        mailSender.send(message);
    }

    @Override
    public void sendPasswordResetEmail(String email, String token) {

        String resetLink =
                frontendUrl + "/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Reset Your Password");

        message.setText(
                "Hello,\n\n" +
                "We received a request to reset your password.\n\n" +
                "Click the link below to reset your password:\n\n" +
                resetLink +
                "\n\nThis link will expire in 15 minutes.\n\n" +
                "If you did not request this, please ignore this email."
        );

        mailSender.send(message);
    }
}