package com.anik.secureauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RefreshTokenResponse {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

}