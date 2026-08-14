package com.anik.secureauth.service;

import com.anik.secureauth.entity.PasswordResetToken;
import com.anik.secureauth.entity.User;

public interface PasswordResetTokenService {

    PasswordResetToken createToken(User user);

    PasswordResetToken findByToken(String token);

    void deleteByUser(User user);
}