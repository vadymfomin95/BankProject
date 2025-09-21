package ua.nure.fomin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Account short info",
        description = "Data Transfer Object for Account that contains minimal necessary information")
public class AccountShortInfoDTO {

    @Schema(description = "Unique identifier for the account", example = "12345")
    private Long accountNumber;

    @Schema(description = "Name of the type of account", example = "Private")
    private String accountType;

    @Schema(description = "Address of the branch where the account is held", example = "123 Main St, Kyiv")
    private String branchAddress;
}
