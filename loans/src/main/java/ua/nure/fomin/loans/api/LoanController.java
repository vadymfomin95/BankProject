package ua.nure.fomin.loans.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fomin.loans.dto.ErrorResponse;
import ua.nure.fomin.loans.dto.LoanCreateRequest;
import ua.nure.fomin.loans.dto.LoanDTO;
import ua.nure.fomin.loans.entity.Loan;
import ua.nure.fomin.loans.mapper.LoanMapper;
import ua.nure.fomin.loans.service.LoanService;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Tag(name = "Loan Management", description = "Operations for managing loans")
public class LoanController {

    private final LoanService loanService;

    private final LoanMapper loanMapper;

    @PostMapping
    @Operation(summary = "Create a new loan",
            description = "Creates a new loan for the customer based on their mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Loan created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Loan already exists for the given mobile number",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public void create(@RequestBody @Valid LoanCreateRequest loanCreateRequest) {
        loanService.create(loanCreateRequest.getMobileNumber());
    }

    @PutMapping("/{mobileNumber}")
    @Operation(summary = "Update an existing loan",
            description = "Updates the details of an existing loan based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Loan updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Loan not found for the given mobile number",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public void update(@PathVariable String mobileNumber, @RequestBody @Valid LoanDTO loanDTO) {
        Loan loan = loanMapper.toEntity(loanDTO);
        loanService.update(mobileNumber, loan);
    }

    @DeleteMapping("/{mobileNumber}")
    @Operation(summary = "Delete a loan",
            description = "Deletes an existing loan based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Loan deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Loan not found for the given mobile number",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable String mobileNumber) {
        loanService.delete(mobileNumber);
    }

    @GetMapping("/{mobileNumber}")
    @Operation(summary = "Find a loan by mobile number",
            description = "Retrieves the loan details based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Loan found successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoanDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Loan not found for the given mobile number",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public LoanDTO findByMobileNumber(@PathVariable String mobileNumber) {
        return loanMapper.toDTO(loanService.findByMobileNumber(mobileNumber));
    }
}
