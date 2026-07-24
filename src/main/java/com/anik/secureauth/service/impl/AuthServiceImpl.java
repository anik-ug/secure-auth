package com.anik.secureauth.service;

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

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;
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
                .build();

        userRepository.save(user);

        return new RegisterResponse(
                "User Registered Successfully",
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
                        new RuntimeException("User not found"));

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
}