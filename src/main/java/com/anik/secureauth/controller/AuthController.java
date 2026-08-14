package com.anik.secureauth.controller;

import com.anik.secureauth.dto.request.LoginRequest;
import com.anik.secureauth.dto.response.LoginResponse;
import com.anik.secureauth.dto.request.RegisterRequest;
import com.anik.secureauth.dto.response.RegisterResponse;
import com.anik.secureauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.anik.secureauth.dto.request.RefreshTokenRequest;
import com.anik.secureauth.dto.response.RefreshTokenResponse;
import org.springframework.http.ResponseEntity;
import com.anik.secureauth.dto.request.LogoutRequest;
import com.anik.secureauth.service.VerificationTokenService;
import org.springframework.web.bind.annotation.RequestParam;
import com.anik.secureauth.dto.request.ForgotPasswordRequest;
import com.anik.secureauth.dto.request.ResetPasswordRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final VerificationTokenService verificationTokenService;

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ){
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                authService.refreshToken(request)
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestBody LogoutRequest request) {

        authService.logout(request);

        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(
            @RequestParam String token
    ) {

        verificationTokenService.verifyToken(token);

        return ResponseEntity.ok("Email verified successfully.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        authService.forgotPassword(request);

        return ResponseEntity.ok(
                "Password reset link has been sent to your email"
        );
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        authService.resetPassword(request);

        return ResponseEntity.ok(
                "Password reset successfully"
        );
    }

}