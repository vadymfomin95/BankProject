package ua.nure.fomin.cards.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.fomin.cards.constans.CardConstants;
import ua.nure.fomin.cards.entity.Card;
import ua.nure.fomin.cards.exception.CardExistException;
import ua.nure.fomin.cards.exception.CardNotFoundException;
import ua.nure.fomin.cards.repository.CardRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public void create(String mobileNumber) {
        if (cardRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new CardExistException("Card already exists for mobile number: " + mobileNumber);
        }

        Card card = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);

        cardRepository.save(card);
    }

    @Transactional
    public void update(String mobileNumber, Card cardToUpdate) {
        cardRepository.findByMobileNumber(mobileNumber)
                .map(existingCard -> {
                    existingCard.setCardType(cardToUpdate.getCardType());
                    existingCard.setTotalLimit(cardToUpdate.getTotalLimit());
                    existingCard.setAvailableAmount(cardToUpdate.getAvailableAmount());
                    existingCard.setAmountUsed(cardToUpdate.getAmountUsed());
                    return cardRepository.save(existingCard);
                })
                .orElseThrow(() -> new CardNotFoundException("Card not found for mobile number: " + mobileNumber));
    }

    @Transactional
    public void delete(String mobileNumber) {
        if (cardRepository.findByMobileNumber(mobileNumber).isEmpty()) {
            throw new CardNotFoundException("Card not found for mobile number: " + mobileNumber);
        }

        cardRepository.deleteByMobileNumber(mobileNumber);
    }

    public Card findByMobileNumber(String mobileNumber) {
        return cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardNotFoundException("Card not found for mobile number: " + mobileNumber));
    }

}
