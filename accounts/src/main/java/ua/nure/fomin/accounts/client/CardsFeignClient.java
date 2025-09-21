package ua.nure.fomin.accounts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.nure.fomin.accounts.entity.Card;

@FeignClient(name = "cards")
public interface CardsFeignClient {

    @GetMapping("/api/cards/{mobileNumber}")
    Card findByMobileNumber(@PathVariable String mobileNumber);
}
