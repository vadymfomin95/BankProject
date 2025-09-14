package ua.nure.fomin.loans.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
@Data
public class LoansProperties {

    private String message;
    private ContactDetails contactDetails;

    @AllArgsConstructor
    @Data
    static class ContactDetails {

        private String name;
        private String email;
    }
}
