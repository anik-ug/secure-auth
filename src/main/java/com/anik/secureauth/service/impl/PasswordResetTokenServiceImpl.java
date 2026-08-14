package com.anik.secureauth.service.impl;

import com.anik.secureauth.entity.PasswordResetToken;
import com.anik.secureauth.entity.User;
import com.anik.secureauth.repository.PasswordResetTokenRepository;
import com.anik.secureauth.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetTokenServiceImpl
        implements PasswordResetTokenService {

    private final PasswordResetTokenRepository repository;

    @Override
    public PasswordResetToken createToken(User user) {

        System.out.println("===== CREATE TOKEN START =====");
        System.out.println("User ID: " + user.getId());
        System.out.println("User Email: " + user.getEmail());

        System.out.println("Deleting old token...");

        repository.deleteByUserId(user.getId());

        System.out.println("Old token deleted.");

        PasswordResetToken token = PasswordResetToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(15))
                .build();

        System.out.println("New token generated: " + token.getToken());
        System.out.println("Expiry: " + token.getExpiryDate());

        PasswordResetToken savedToken = repository.save(token);

        System.out.println("Token saved!");
        System.out.println("Saved ID: " + savedToken.getId());
        System.out.println("Saved Token: " + savedToken.getToken());

        System.out.println("===== CREATE TOKEN END =====");

        return savedToken;
    }

    @Override
    public PasswordResetToken findByToken(String token) {

        return repository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid password reset token"));
    }

    @Override
    public void deleteByUser(User user) {
        repository.deleteByUserId(user.getId());
    }
}