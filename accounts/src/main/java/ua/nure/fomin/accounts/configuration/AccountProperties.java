package ua.nure.fomin.accounts.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountProperties {

    private String message;
    private ContactDetails contactDetails;

    @AllArgsConstructor
    @Data
    static class ContactDetails {

        private String name;
        private String email;
    }
}
