package com.anik.secureauth.service.impl;


import com.anik.secureauth.entity.RefreshToken;
import com.anik.secureauth.entity.User;
import com.anik.secureauth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import com.anik.secureauth.service.RefreshTokenService;
import com.anik.secureauth.exception.RefreshTokenExpiredException;
import com.anik.secureauth.exception.RefreshTokenNotFoundException;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    // 7 days
    private static final long REFRESH_TOKEN_DURATION_DAYS = 7;

    @Override
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_DURATION_DAYS))
                .user(user)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(token);

            throw new RefreshTokenExpiredException("Refresh token has expired. Please login again.");
        }

        return token;
    }

    @Override
    public RefreshToken findByToken(String token) {

        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RefreshTokenNotFoundException("Refresh token not found"));
    }

    @Override
    public void deleteByUser(User user) {

        refreshTokenRepository.deleteByUser(user);
    }
}