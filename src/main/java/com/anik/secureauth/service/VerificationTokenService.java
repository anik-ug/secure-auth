package com.anik.secureauth.service;

import com.anik.secureauth.entity.User;
import com.anik.secureauth.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(User user);

    VerificationToken findByToken(String token);

    void deleteByUser(User user);

    void verifyToken(String token);
}