package ua.nure.fomin.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoanCreateRequest {

    @Pattern(regexp = "^(\\+?380|0)\\d{9}$", message = "Mobile number must be in Ukrainian format: +380XXXXXXXXX or 0XXXXXXXXX")
    @Schema(description = "Mobile number of the customer", example = "0963883679")
    private String mobileNumber;
}
