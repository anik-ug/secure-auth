package com.anik.secureauth.service;

import com.anik.secureauth.dto.response.UserProfileResponse;

public interface UserService {

    UserProfileResponse getCurrentUser();
}