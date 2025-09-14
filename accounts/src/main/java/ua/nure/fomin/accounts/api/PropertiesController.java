package ua.nure.fomin.accounts.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.fomin.accounts.configuration.AccountProperties;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final AccountProperties accountProperties;

    @GetMapping
    public AccountProperties getAccountProperties() {
        return accountProperties;
    }
}
