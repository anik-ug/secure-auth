package com.anik.secureauth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {

    private String name;
    private String email;
    private String role;
}