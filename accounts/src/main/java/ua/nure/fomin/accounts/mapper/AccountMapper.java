package ua.nure.fomin.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.fomin.accounts.dto.AccountDTO;
import ua.nure.fomin.accounts.dto.AccountDetailsDTO;
import ua.nure.fomin.accounts.dto.AccountShortInfoDTO;
import ua.nure.fomin.accounts.entity.Account;
import ua.nure.fomin.accounts.entity.AccountDetails;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "account.customer.name", target = "name")
    @Mapping(source = "account.customer.email", target = "email")
    @Mapping(source = "account.customer.mobileNumber", target = "mobileNumber")
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.accountType", target = "accountType")
    @Mapping(source = "account.branchAddress", target = "branchAddress")
    AccountDetailsDTO toAccountDetailsDTO(AccountDetails accountDetails);

    AccountShortInfoDTO toShortInfoDTO(Account account);

    @Mapping(source = "name", target = "customer.name")
    @Mapping(source = "email", target = "customer.email")
    @Mapping(source = "mobileNumber", target = "customer.mobileNumber")
    Account toEntity(AccountDTO dto);

}
