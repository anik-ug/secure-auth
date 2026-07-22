package com.anik.secureauth.service;

import com.anik.secureauth.dto.request.LoginRequest;
import com.anik.secureauth.dto.request.RegisterRequest;
import com.anik.secureauth.dto.response.LoginResponse;
import com.anik.secureauth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}