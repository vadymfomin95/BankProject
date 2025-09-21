package ua.nure.fomin.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "Card",
        description = "Data Transfer Object for Card that contains card specific details")
public class CardDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique identifier for the card", example = "12345")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Mobile number associated with the card", example = "+380963883679")
    private String mobileNumber;

    @NotBlank(message = "Card number cannot be blank")
    @Schema(description = "Card number", example = "123456789012")
    private String cardNumber;

    @NotBlank(message = "Card type cannot be blank")
    @Schema(description = "Type of the card", example = "Credit")
    private String cardType;

    @NotNull
    @Min(value = 0, message = "Total limit must be a non-negative integer")
    @Max(value = 100000000, message = "Total limit must not exceed 100,000,000")
    @Schema(description = "Total limit of the card", example = "1000000")
    private Integer totalLimit;

    @NotNull
    @Min(value = 0, message = "Total limit must be a non-negative integer")
    @Max(value = 100000000, message = "Total limit must not exceed 100,000,000")
    @Schema(description = "Available amount on the card", example = "500000")
    private Integer availableAmount;

    @NotNull
    @Min(value = 0, message = "Total limit must be a non-negative integer")
    @Max(value = 100000000, message = "Total limit must not exceed 100,000,000")
    @Schema(description = "Amount used from the card", example = "500000")
    private Integer amountUsed;
}
