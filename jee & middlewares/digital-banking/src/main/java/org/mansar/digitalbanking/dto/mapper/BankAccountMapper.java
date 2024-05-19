package org.mansar.digitalbanking.dto.mapper;

import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.CurrentAccount;
import org.mansar.digitalbanking.model.SavingAccount;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankAccountMapper extends IMapper<BankAccount, BankAccountDTO> {
    @AfterMapping
    default void afterMapping(BankAccount bankAccount, @MappingTarget BankAccountDTO dto) {
        switch (bankAccount) {
            case CurrentAccount currentAccount -> dto.setOverDraft(currentAccount.getOverDraft());
            case SavingAccount savingAccount -> dto.setInterestRate(savingAccount.getInterestRate());
            default -> {}
        }
    }
}
