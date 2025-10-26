package com.cherrymarket.dto.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(name = "User", description = "User entity transfer object description")
public class UserDto {

    @Schema(description = "User identifier", example = "d512a3bc-7a6e-4e9f-98e5-73f361e1af1e")
    private UUID id;

    @Schema(description = "Username", example = "Anna")
    @NotNull(message = "{validation.user-dto.username.not-null}")
    @NotBlank(message = "{validation.user-dto.username.not-blank}")
    @Pattern(regexp = "^(?! )[a-zA-Z0-9 ]*(?<! )$", message = "{validation.user-dto.username.pattern}")
    @Size(min = 3, max = 255, message = "{validation.user-dto.username.size}")
    private String username;

    @Schema(description = "User email", example = "anna@gmail.com")
    @NotNull(message = "{validation.user-dto.email.not-null}")
    @NotBlank(message = "{validation.user-dto.email.not-blank}")
    @Email(message = "{validation.user-dto.email.pattern}")
    @Size(min = 5, max = 255, message = "{validation.user-dto.email.size}")
    private String email;

    @Schema(description = "User phone number", example = "+380111111111")
    @NotNull(message = "{validation.user-dto.phone.not-null}")
    @Pattern(regexp = "^$|^(?! )\\+[1-9]\\d{1,14}(?<! )$", message = "{validation.user-dto.phone.pattern}")
    private String phone;

    @Schema(description = "User password", example = "secret password")
    @NotNull(message = "{validation.user-dto.password.not-null}")
    @NotBlank(message = "{validation.user-dto.password.not-blank}")
    @Size(min = 8, max = 255, message = "{validation.user-dto.password.size}")
    private String password;

    @Schema(description = "User activity flag", example = "true")
    @NotNull(message = "{validation.user-dto.active.not-null}")
    private Boolean active;

    @Schema(description = "Market identifier", example = "d512a3bc-7a6e-4e9f-98e5-73f361e1af1e")
    @NotNull(message = "{validation.user-dto.market-id.not-null}")
    private UUID marketId;

}
