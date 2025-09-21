package ua.nure.fomin.accounts.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fomin.accounts.dto.AccountDTO;
import ua.nure.fomin.accounts.dto.AccountShortInfoDTO;
import ua.nure.fomin.accounts.dto.ErrorResponse;
import ua.nure.fomin.accounts.entity.Account;
import ua.nure.fomin.accounts.entity.AccountDetails;
import ua.nure.fomin.accounts.mapper.AccountMapper;
import ua.nure.fomin.accounts.service.AccountDetailsService;
import ua.nure.fomin.accounts.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Management", description = "APIs for account operations")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountDetailsService accountDetailsService;

    private final AccountMapper accountMapper;

    @GetMapping
    @Operation(summary = "Get all accounts", description = "Retrieves a list of all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccountShortInfoDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<AccountShortInfoDTO> getAllAccounts() {
        return accountService.findAll()
                .stream()
                .map(accountMapper::toShortInfoDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Create a new account", description = "Saves a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void save(@RequestBody @Valid AccountDTO accountDetails) {
        Account account = accountMapper.toEntity(accountDetails);
        accountService.save(account);
    }

    @PutMapping("/{accountNumber}")
    @Operation(summary = "Update an existing account", description = "Updates the details of an existing account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void update(@PathVariable @Parameter(name = "accountNumber", description = "account number to update") Long accountNumber,
                       @RequestBody @Valid AccountDTO accountDetails) {
        Account accountToUpdate = accountMapper.toEntity(accountDetails);
        accountService.update(accountNumber, accountToUpdate);
    }

    @DeleteMapping("/{accountNumber}")
    @Operation(summary = "Delete an account", description = "Deletes an account by its account number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable @Parameter(name = "accountNumber", description = "account number to delete") Long accountNumber) {
        accountService.delete(accountNumber);
    }

    @GetMapping("/{mobilePhone}/details")
    @Operation(summary = "Get account details by mobile phone", description = "Retrieves account details using the mobile phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account details",
                    content = @Content(schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public AccountDTO getAccountDetails(@PathVariable @Parameter(name = "mobilePhone", description = "mobile phone to search") String mobilePhone) {
        AccountDetails details = accountDetailsService.getDetails(mobilePhone);
        return accountMapper.toAccountDetailsDTO(details);
    }

}
