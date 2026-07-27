package com.anik.secureauth.security;

import com.anik.secureauth.entity.Role;
import com.anik.secureauth.entity.User;
import com.anik.secureauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String adminEmail = "admin@secureauth.com";

        if (!userRepository.existsByEmail(adminEmail)) {

            User admin = User.builder()
                    .name("Admin")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("Admin@123"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);

            System.out.println("✅ Default Admin Created Successfully");
        } else {
            System.out.println("ℹ️ Admin Already Exists");
        }
    }
}