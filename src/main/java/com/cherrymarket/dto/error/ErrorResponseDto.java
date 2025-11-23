package com.cherrymarket.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Error response", description = "Basic error response transfer object description")
public class ErrorResponseDto {

    @Schema(description = "Error response http status", example = "401")
    private Integer status;

    @Schema(description = "Error response message", example = "Unauthorized")
    private String message;

    @Schema(description = "Error response time in unix milliseconds", example = "1763907192917")
    private Long time;

}
