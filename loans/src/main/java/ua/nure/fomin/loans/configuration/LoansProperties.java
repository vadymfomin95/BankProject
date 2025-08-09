package ua.nure.fomin.loans.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
public record LoansProperties(String message, ContactDetails contactDetails) {

    public record ContactDetails(String name, String email) {
    }
}
