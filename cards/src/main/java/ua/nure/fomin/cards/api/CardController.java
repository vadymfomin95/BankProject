package ua.nure.fomin.cards.api;

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
import ua.nure.fomin.cards.dto.CardCreateRequest;
import ua.nure.fomin.cards.dto.CardDTO;
import ua.nure.fomin.cards.dto.ErrorResponse;
import ua.nure.fomin.cards.entity.Card;
import ua.nure.fomin.cards.mapper.CardMapper;
import ua.nure.fomin.cards.service.CardService;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
@Tag(name = "Card Management", description = "Operations for managing cards")
public class CardController {

    private final CardService cardService;

    private final CardMapper cardMapper;

    @PostMapping
    @Operation(summary = "Create a new card",
               description = "Creates a new card for the customer based on their mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Card created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Card already exists for the given mobile number")
    })
    public void create(@RequestBody @Valid CardCreateRequest cardCreateRequest) {
        cardService.create(cardCreateRequest.getMobileNumber());
    }

    @PutMapping("/{mobileNumber}")
    @Operation(summary = "Update an existing card",
               description = "Updates the details of an existing card based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Card updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Card not found for the given mobile number",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public void update(@PathVariable String mobileNumber, @RequestBody @Valid CardDTO cardDTO) {
        Card card = cardMapper.toEntity(cardDTO);
        cardService.update(mobileNumber, card);
    }

    @DeleteMapping("/{mobileNumber}")
    @Operation(summary = "Delete a card",
               description = "Deletes an existing card based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Card deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Card not found for the given mobile number",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable String mobileNumber) {
        cardService.delete(mobileNumber);
    }

    @GetMapping("/{mobileNumber}")
    @Operation(summary = "Find a card by mobile number",
               description = "Retrieves the card details based on the mobile number")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Card found successfully",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CardDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Card not found for the given mobile number",
            content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class)))
    })
    public CardDTO findByMobileNumber(@PathVariable String mobileNumber) {
        return cardMapper.toDTO(cardService.findByMobileNumber(mobileNumber));
    }
}
