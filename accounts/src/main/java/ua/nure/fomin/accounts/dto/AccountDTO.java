package ua.nure.fomin.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Data Transfer Object for Account that contains account and customer details")
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Unique identifier for the account", example = "12345")
    private Long accountNumber;

    @NotBlank(message = "Account number cannot be blank")
    @Schema(description = "Name of the type of account", example = "Private")
    private String accountType;

    @NotBlank(message = "Branch address cannot be blank")
    @Schema(description = "Address of the branch where the account is held", example = "123 Main St, Kyiv")
    private String branchAddress;

    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "Name of the customer", example = "John Doe")
    private String name;

    @Email(message = "Email should be valid")
    @Schema(description = "Email of the customer", example = "vadym.fomin@nure.ua")
    private String email;

    @Pattern(regexp = "^(\\+?380|0)\\d{9}$", message = "Mobile number must be in Ukrainian format: +380XXXXXXXXX or 0XXXXXXXXX")
    @Schema(description = "Mobile number of the customer", example = "0963883679")
    private String mobileNumber;
}
