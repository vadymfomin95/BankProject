package ua.nure.fomin.loans.mapper;

import org.mapstruct.Mapper;
import ua.nure.fomin.loans.dto.LoanDTO;
import ua.nure.fomin.loans.entity.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toEntity(LoanDTO loanDto);

    LoanDTO toDTO(Loan loan);
}
