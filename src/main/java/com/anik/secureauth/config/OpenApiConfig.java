package com.anik.secureauth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI secureAuthAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("SecureAuth API")
                        .description("JWT Authentication & Authorization REST API")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Anik Kumar")
                                .email("anik@example.com"))).addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .schemaRequirement(
                        SCHEME_NAME,
                        new SecurityScheme()
                                .name(SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }
}