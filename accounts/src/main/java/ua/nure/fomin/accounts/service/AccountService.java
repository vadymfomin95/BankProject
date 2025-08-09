package ua.nure.fomin.accounts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.fomin.accounts.entity.Account;
import ua.nure.fomin.accounts.entity.Customer;
import ua.nure.fomin.accounts.exception.AccountsNotExistException;
import ua.nure.fomin.accounts.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByMobileNumber(String mobileNumber) {
        return accountRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new AccountsNotExistException("Account with mobileNumber " + mobileNumber + " not found"));
    }

    @Transactional
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void update(Long accountNumber, Account accountToUpdate) {
        accountRepository.findByAccountNumber(accountNumber)
                .map(existingAccount -> {
                    existingAccount.setAccountType(accountToUpdate.getAccountType());
                    existingAccount.setBranchAddress(accountToUpdate.getBranchAddress());

                    Customer existingCustomer = existingAccount.getCustomer();
                    existingCustomer.setName(accountToUpdate.getCustomer().getName());
                    existingCustomer.setEmail(accountToUpdate.getCustomer().getEmail());
                    existingCustomer.setMobileNumber(accountToUpdate.getCustomer().getMobileNumber());

                    return accountRepository.save(existingAccount);
                })
                .orElseThrow(() -> new AccountsNotExistException("Account not found with number: " + accountNumber));
    }

    @Transactional
    public void delete(Long accountNumber) {
        int deletedRecordsCount = accountRepository.deleteByAccountNumber(accountNumber);
        if (deletedRecordsCount == 0) {
            throw new AccountsNotExistException("Account not found with number: " + accountNumber);
        }
    }
}
