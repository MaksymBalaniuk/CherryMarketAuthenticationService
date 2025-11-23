package com.cherrymarket.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(name = "Authentication response", description = "Authentication response transfer object description")
public class AuthenticationResponseDto {

    @Schema(description = "JWT for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5...")
    private String token;

    @Schema(description = "User identifier", example = "d512a3bc-7a6e-4e9f-98e5-73f361e1af1e")
    private UUID id;

}
