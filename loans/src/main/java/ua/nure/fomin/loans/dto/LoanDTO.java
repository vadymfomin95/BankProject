package ua.nure.fomin.loans.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "Loan",
        description = "Data Transfer Object for Loan that contains loan specific details")
public class LoanDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique identifier for the loan", example = "12345")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Mobile number associated with the loan", example = "+380963883679")
    private String mobileNumber;

    @NotBlank(message = "Loan number cannot be blank")
    @Schema(description = "Loan number", example = "123456789012")
    private String loanNumber;

    @NotBlank(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;

    @NotNull
    @Min(value = 0, message = "Total loan must be a non-negative integer")
    @Max(value = 100000000, message = "Total loan must not exceed 100,000,000")
    @Schema(description = "Total limit of the loan", example = "1000000")
    private Integer totalLoan;

    @NotNull
    @Min(value = 0, message = "Amount paid must be a non-negative integer")
    @Max(value = 100000000, message = "Amount paid must not exceed 100,000,000")
    @Schema(description = "Amount paid for the loan", example = "500000")
    private Integer amountPaid;

    @NotNull
    @Min(value = 0, message = "Outstanding amount must be a non-negative integer")
    @Max(value = 100000000, message = "Outstanding amount must not exceed 100,000,000")
    @Schema(description = "Outstanding amount of the loan", example = "500000")
    private Integer outstandingAmount;
}
