package ua.nure.fomin.accounts.entity;

import lombok.Data;

@Data
public class AccountDetails {

    private Account account;
    private Card card;
    private Loan loan;
}
