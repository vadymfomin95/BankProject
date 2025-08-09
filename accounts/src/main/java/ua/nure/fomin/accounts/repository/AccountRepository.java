package ua.nure.fomin.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nure.fomin.accounts.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a JOIN FETCH a.customer c WHERE c.mobileNumber = :mobileNumber")
    Optional<Account> findByMobileNumber(String mobileNumber);

    @Query("SELECT a FROM Account a JOIN FETCH a.customer c WHERE a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumber(Long accountNumber);

    int deleteByAccountNumber(Long accountNumber);

}
