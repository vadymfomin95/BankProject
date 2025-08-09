package ua.nure.fomin.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.fomin.loans.entity.Loan;

import java.util.Optional;

@Repository
public interface LoadRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByMobileNumber(String mobileNumber);

    void deleteByMobileNumber(String mobileNumber);
}
