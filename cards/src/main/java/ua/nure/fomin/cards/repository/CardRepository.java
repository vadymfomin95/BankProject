package ua.nure.fomin.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.fomin.cards.entity.Card;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByMobileNumber(String mobileNumber);

    void deleteByMobileNumber(String mobileNumber);
}
