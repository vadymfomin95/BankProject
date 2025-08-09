package ua.nure.fomin.accounts.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Accounts Management API")
                        .description("API for managing customer bank accounts")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vadym Fomin")
                                .email("vadym.fomin@nure.ua")));
    }
}