package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.exception.BalanceNotSufficientException;
import org.mansar.digitalbanking.exception.BankAccountNotFoundException;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.CurrentAccount;
import org.mansar.digitalbanking.model.SavingAccount;

import java.util.List;

public interface IBankService {
    CurrentAccount saveCurrentAccount(double initAmount, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingAccount(double initAmount, double interestRate, Long customerId) throws CustomerNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccount> getAccounts();
}
