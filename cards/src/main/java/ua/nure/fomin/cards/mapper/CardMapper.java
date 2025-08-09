package ua.nure.fomin.cards.mapper;

import org.mapstruct.Mapper;
import ua.nure.fomin.cards.dto.CardDTO;
import ua.nure.fomin.cards.entity.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toEntity(CardDTO cardDTO);

    CardDTO toDTO(Card card);
}
