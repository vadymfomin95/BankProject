package ua.nure.fomin.loans.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.fomin.loans.constants.LoanConstants;
import ua.nure.fomin.loans.entity.Loan;
import ua.nure.fomin.loans.exception.LoanExistException;
import ua.nure.fomin.loans.exception.LoanNotFoundException;
import ua.nure.fomin.loans.repository.LoadRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoadRepository loadRepository;

    @Transactional
    public void create(String mobileNumber) {
        if (loadRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new LoanExistException("Loan already exists for mobile number: " + mobileNumber);
        }

        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        loadRepository.save(newLoan);
    }

    @Transactional
    public void update(String mobileNumber, Loan loanToUpdate) {
        loadRepository.findByMobileNumber(mobileNumber)
                .map(existingLoan -> {
                    existingLoan.setLoanType(loanToUpdate.getLoanType());
                    existingLoan.setLoanNumber(loanToUpdate.getLoanNumber());
                    existingLoan.setAmountPaid(loanToUpdate.getAmountPaid());
                    existingLoan.setTotalLoan(loanToUpdate.getTotalLoan());
                    existingLoan.setOutstandingAmount(loanToUpdate.getOutstandingAmount());
                    return loadRepository.save(existingLoan);
                })
                .orElseThrow(() -> new LoanNotFoundException("Loan not found for mobile number: " + mobileNumber));
    }

    @Transactional
    public void delete(String mobileNumber) {
        if (loadRepository.findByMobileNumber(mobileNumber).isEmpty()) {
            throw new LoanNotFoundException("Loan not found for mobile number: " + mobileNumber);
        }

        loadRepository.deleteByMobileNumber(mobileNumber);
    }

    public Loan findByMobileNumber(String mobileNumber) {
        return loadRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found for mobile number: " + mobileNumber));
    }

}
