package ua.nure.fomin.cards.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cards")
@Data
public class CardsProperties {

    private String message;
    private ContactDetails contactDetails;

    @AllArgsConstructor
    @Data
    static class ContactDetails {

        private String name;
        private String email;
    }
}
