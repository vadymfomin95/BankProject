package ua.nure.fomin.loans.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loans Management API")
                        .description("API for managing customer loans")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vadym Fomin")
                                .email("vadym.fomin@nure.ua")));
    }
}