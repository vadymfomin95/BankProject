package ua.nure.fomin.accounts.mapper;

import org.mapstruct.Mapper;
import ua.nure.fomin.accounts.dto.AccountDTO;
import ua.nure.fomin.accounts.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO toDTO(Account account);
}
