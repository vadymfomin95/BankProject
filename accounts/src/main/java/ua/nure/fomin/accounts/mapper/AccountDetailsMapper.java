package ua.nure.fomin.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.fomin.accounts.dto.AccountDetailsDTO;
import ua.nure.fomin.accounts.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {

    @Mapping(source = "customer.name", target = "name")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.mobileNumber", target = "mobileNumber")
    AccountDetailsDTO toDTO(Account account);

    @Mapping(source = "name", target = "customer.name")
    @Mapping(source = "email", target = "customer.email")
    @Mapping(source = "mobileNumber", target = "customer.mobileNumber")
    Account toEntity(AccountDetailsDTO dto);

}
