package com.cherrymarket.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(name = "Authentication request", description = "Authentication request transfer object description")
public class AuthenticationRequestDto {

    @Schema(description = "User email", example = "anna@gmail.com")
    @NotNull(message = "{validation.user-dto.email.not-null}")
    @NotBlank(message = "{validation.user-dto.email.not-blank}")
    @Email(message = "{validation.user-dto.email.pattern}")
    @Size(min = 5, max = 255, message = "{validation.user-dto.email.size}")
    private String email;

    @Schema(description = "User password", example = "secret password")
    @NotNull(message = "{validation.user-dto.password.not-null}")
    @NotBlank(message = "{validation.user-dto.password.not-blank}")
    @Size(min = 8, max = 255, message = "{validation.user-dto.password.size}")
    private String password;

    @Schema(description = "Market identifier", example = "d512a3bc-7a6e-4e9f-98e5-73f361e1af1e")
    @NotNull(message = "{validation.user-dto.market-id.not-null}")
    private UUID marketId;

}
