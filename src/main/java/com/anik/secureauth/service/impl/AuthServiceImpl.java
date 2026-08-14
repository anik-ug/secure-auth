package com.anik.secureauth.service.impl;

import com.anik.secureauth.service.RefreshTokenService;
import com.anik.secureauth.service.AuthService;
import com.anik.secureauth.exception.ResourceAlreadyExistsException;
import com.anik.secureauth.dto.request.RegisterRequest;
import com.anik.secureauth.dto.response.RegisterResponse;
import com.anik.secureauth.entity.Role;
import com.anik.secureauth.entity.User;
import com.anik.secureauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.anik.secureauth.dto.request.LoginRequest;
import com.anik.secureauth.dto.response.LoginResponse;
import com.anik.secureauth.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.anik.secureauth.dto.request.RefreshTokenRequest;
import com.anik.secureauth.dto.response.RefreshTokenResponse;
import com.anik.secureauth.entity.RefreshToken;
import org.springframework.security.core.userdetails.UserDetails;
import com.anik.secureauth.security.service.CustomUserDetailsService;
import com.anik.secureauth.dto.request.LogoutRequest;
import com.anik.secureauth.exception.UserNotFoundException;
import com.anik.secureauth.service.EmailService;
import com.anik.secureauth.service.VerificationTokenService;
import com.anik.secureauth.entity.VerificationToken;
import com.anik.secureauth.dto.request.ForgotPasswordRequest;
import com.anik.secureauth.dto.request.ResetPasswordRequest;
import com.anik.secureauth.entity.PasswordResetToken;
import com.anik.secureauth.service.PasswordResetTokenService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final PasswordResetTokenService passwordResetTokenService;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(false)
                .build();

        userRepository.save(user);
        
        VerificationToken verificationToken =
            verificationTokenService.createVerificationToken(user);

        emailService.sendVerificationEmail(
                user.getEmail(),
                user.getName(),
                verificationToken.getToken()
        );

        return new RegisterResponse(
                "Registration successful. Please verify your email.",
                user.getEmail()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities("ROLE_" + user.getRole().name())
                        .build()
        );

        RefreshToken refreshToken =
        refreshTokenService.createRefreshToken(user);

        return LoginResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken.getToken())
        .tokenType("Bearer")
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
    }
        @Override
        public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService.findByToken(
                request.getRefreshToken());

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(user.getEmail());

        String accessToken = jwtService.generateToken(userDetails);

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .build();
        }

        @Override
        public void logout(LogoutRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.findByToken(request.getRefreshToken());

        refreshTokenService.deleteByUser(refreshToken.getUser());
        }

        @Override
        public void verifyEmail(String token) {

        VerificationToken verificationToken =
                verificationTokenService.findByToken(token);

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification token expired");
        }

        User user = verificationToken.getUser();

        user.setEnabled(true);
        userRepository.save(user);

        verificationTokenService.deleteByUser(user);
        }
        // @Override
        // public void forgotPassword(ForgotPasswordRequest request) {

        //         User user = userRepository.findByEmail(request.getEmail())
        //                 .orElseThrow(() ->
        //                         new UserNotFoundException("User not found"));

        //         PasswordResetToken resetToken =
        //                 passwordResetTokenService.createToken(user);

        //         emailService.sendPasswordResetEmail(
        //                 user.getEmail(),
        //                 resetToken.getToken()
        //         );
        // }
        @Override
        public void forgotPassword(ForgotPasswordRequest request) {

        System.out.println("===== FORGOT PASSWORD START =====");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        System.out.println("USER FOUND: " + user.getId());

        PasswordResetToken resetToken =
                passwordResetTokenService.createToken(user);

        System.out.println(
                "RESET TOKEN RETURNED: " + resetToken.getToken()
        );

        System.out.println("Sending reset email...");

        emailService.sendPasswordResetEmail(
                user.getEmail(),
                resetToken.getToken()
        );

        System.out.println("RESET EMAIL SENT");

        System.out.println("===== FORGOT PASSWORD END =====");
        }
        @Override
        public void resetPassword(ResetPasswordRequest request) {

        PasswordResetToken resetToken =
                passwordResetTokenService.findByToken(request.getToken());

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {

                passwordResetTokenService.deleteByUser(
                        resetToken.getUser()
                );

                throw new RuntimeException("Password reset token expired");
        }

        User user = resetToken.getUser();

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        // Token can no longer be reused
        passwordResetTokenService.deleteByUser(user);
        }
}