package ua.nure.fomin.accounts.dto;

import lombok.Data;

@Data
public class AccountDetailsDTO extends AccountDTO {

    private CardDTO card;
    private LoanDTO loan;
}
