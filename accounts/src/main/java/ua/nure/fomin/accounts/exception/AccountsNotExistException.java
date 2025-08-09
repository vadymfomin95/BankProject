package ua.nure.fomin.accounts.exception;

public class AccountsNotExistException extends RuntimeException {

    public AccountsNotExistException(String message) {
        super(message);
    }
}
