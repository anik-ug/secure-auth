package com.anik.secureauth.service;

import com.anik.secureauth.entity.RefreshToken;
import com.anik.secureauth.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshToken findByToken(String token);

    void deleteByUser(User user);

}