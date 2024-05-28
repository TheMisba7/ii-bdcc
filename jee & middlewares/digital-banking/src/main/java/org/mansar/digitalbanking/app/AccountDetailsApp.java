package org.mansar.digitalbanking.app;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dto.OperationDTO;
import org.mansar.digitalbanking.exception.BankAccountNotFoundException;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.exception.Messages;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsApp {
    private final IBankService bankService;

    public void createOperation(OperationDTO operationDTO) {
        switch (operationDTO.getType()) {
            case CREDIT -> bankService.credit(
                    operationDTO.getAccountId(),
                    operationDTO.getAmount(),
                    operationDTO.getDescription());
            case DEBIT -> bankService.debit(
                    operationDTO.getAccountId(),
                    operationDTO.getAmount(),
                    operationDTO.getDescription());
            case Transfer -> {
                if (operationDTO.getDesAccount() == null
                        || operationDTO.getDesAccount().isBlank()) {
                    throw new BankAccountNotFoundException(Messages.ACCOUNT_NOT_PRESENT, 401);
                }
                bankService.transfer(
                        operationDTO.getDesAccount(),
                        operationDTO.getDesAccount(),
                        operationDTO.getAmount(),
                        operationDTO.getDescription()
                );
            }
        }
    }
}
