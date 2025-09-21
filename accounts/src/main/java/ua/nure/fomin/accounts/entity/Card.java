package ua.nure.fomin.accounts.entity;

import lombok.Data;

@Data
public class Card {

    private Long id;
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer availableAmount;
    private Integer amountUsed;
}
