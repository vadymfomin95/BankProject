package ua.nure.fomin.accounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.fomin.accounts.client.CardsFeignClient;
import ua.nure.fomin.accounts.client.LoansFeignClient;
import ua.nure.fomin.accounts.entity.Account;
import ua.nure.fomin.accounts.entity.AccountDetails;
import ua.nure.fomin.accounts.entity.Card;
import ua.nure.fomin.accounts.entity.Loan;
import ua.nure.fomin.accounts.repository.AccountRepository;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AccountDetailsService {

    private final AccountRepository accountRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    public AccountDetails getDetails(String phoneNumber) {
        CompletableFuture<Account> accountFuture = CompletableFuture.supplyAsync(() -> accountRepository.findByMobileNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Account with mobile number " + phoneNumber + " not found")));

        CompletableFuture<Card> cardFuture = CompletableFuture.supplyAsync(() -> cardsFeignClient.findByMobileNumber(phoneNumber));
        CompletableFuture<Loan> loansFuture = CompletableFuture.supplyAsync(() -> loansFeignClient.findByMobileNumber(phoneNumber));

        return CompletableFuture.allOf(accountFuture, cardFuture, loansFuture).thenApply(v -> {
                    AccountDetails accountDetails = new AccountDetails();
                    accountDetails.setAccount(accountFuture.join());
                    accountDetails.setCard(cardFuture.join());
                    accountDetails.setLoan(loansFuture.join());
                    return accountDetails;
                })
                .join();
    }
}
