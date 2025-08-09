package ua.nure.fomin.loans.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fomin.loans.configuration.LoansProperties;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final LoansProperties loansProperties;

    @GetMapping
    public LoansProperties getLoansProperties() {
        return loansProperties;
    }
}
