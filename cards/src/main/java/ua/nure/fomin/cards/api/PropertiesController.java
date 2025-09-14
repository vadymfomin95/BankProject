package ua.nure.fomin.cards.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fomin.cards.configuration.CardsProperties;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final CardsProperties cardsProperties;

    @GetMapping
    public CardsProperties getCardsProperties() {
        return cardsProperties;
    }
}
