package ua.nure.fomin.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Data Transfer Object for error responses containing error message and timestamp")
public class ErrorResponse {

    @Schema(description = "Description of error", example = "Invalid input")
    private String message;

    @Schema(description = "Error timestamp", example = "2025-01-01T12:00:00Z")
    private String timestamp;
}
