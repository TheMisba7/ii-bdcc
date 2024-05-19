package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.exception.BalanceNotSufficientException;
import org.mansar.digitalbanking.exception.BankAccountNotFoundException;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.model.BankAccount;

import java.util.List;

public interface IBankService {
     default BankAccountDTO create(BankAccountDTO account) throws RuntimeException {
         if(account.getType() == null) {
             throw new RuntimeException("Account type required");
         }

         return switch (account.getType()) {
             case SAVING -> saveSavingAccount(account.getBalance(), account.getInterestRate(), account.getCustomerId());
             case CURRENT -> saveCurrentAccount(account.getBalance(), account.getOverDraft(), account.getCustomerId());
             default -> throw new RuntimeException("Unknown Account type");
         };
     }
    BankAccountDTO saveCurrentAccount(double initAmount, double overDraft, Long customerId) throws CustomerNotFoundException;
    BankAccountDTO saveSavingAccount(double initAmount, double interestRate, Long customerId) throws CustomerNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccount> getAccounts();
    BankAccountDTO getAccount(String id);
}
