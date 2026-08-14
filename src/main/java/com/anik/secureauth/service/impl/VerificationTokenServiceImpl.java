package com.anik.secureauth.service.impl;

import com.anik.secureauth.entity.User;
import com.anik.secureauth.entity.VerificationToken;
import com.anik.secureauth.repository.VerificationTokenRepository;
import com.anik.secureauth.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.anik.secureauth.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl
        implements VerificationTokenService {

    private final VerificationTokenRepository repository;
    private final UserRepository userRepository;

    @Override
    public VerificationToken createVerificationToken(User user) {

        repository.deleteByUserId(user.getId());

        VerificationToken token = VerificationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .build();

        return repository.save(token);
    }

    @Override
    public VerificationToken findByToken(String token) {

        return repository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid verification token"));
    }

    @Override
    public void deleteByUser(User user) {
        repository.deleteByUserId(user.getId());
    }

    @Override
    public void verifyToken(String token) {

        VerificationToken verificationToken = findByToken(token);

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification token has expired.");
        }

        User user = verificationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        repository.delete(verificationToken);
    }
}