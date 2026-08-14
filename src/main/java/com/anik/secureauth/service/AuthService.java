package com.anik.secureauth.service;

import com.anik.secureauth.dto.request.LoginRequest;
import com.anik.secureauth.dto.request.RegisterRequest;
import com.anik.secureauth.dto.response.LoginResponse;
import com.anik.secureauth.dto.response.RegisterResponse;
import com.anik.secureauth.dto.request.RefreshTokenRequest;
import com.anik.secureauth.dto.response.RefreshTokenResponse;
import com.anik.secureauth.dto.request.LogoutRequest;
import com.anik.secureauth.dto.request.ForgotPasswordRequest;
import com.anik.secureauth.dto.request.ResetPasswordRequest;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void verifyEmail(String token);

    void logout(LogoutRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);
}