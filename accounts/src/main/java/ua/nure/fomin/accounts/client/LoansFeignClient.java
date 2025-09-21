package ua.nure.fomin.accounts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.nure.fomin.accounts.entity.Loan;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @GetMapping("/api/loans/{mobileNumber}")
    Loan findByMobileNumber(@PathVariable String mobileNumber);
}
