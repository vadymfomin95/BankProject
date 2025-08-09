package ua.nure.fomin.loans.exception;

public class LoanExistException extends RuntimeException {

    public LoanExistException(String message) {
        super(message);
    }
}
