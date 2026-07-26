package com.anik.secureauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(Authentication authentication) {

        return Map.of(
                "email", authentication.getName(),
                "message", "JWT Authentication Working Successfully"
        );
    }
}