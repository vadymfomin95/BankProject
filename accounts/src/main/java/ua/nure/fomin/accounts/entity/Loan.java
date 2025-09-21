package ua.nure.fomin.accounts.entity;

import lombok.Data;

@Data
public class Loan {

    private Long id;
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Integer totalLoan;
    private Integer amountPaid;
    private Integer outstandingAmount;
}
