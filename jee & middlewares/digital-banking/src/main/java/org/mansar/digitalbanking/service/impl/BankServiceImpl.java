package org.mansar.digitalbanking.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dao.OperationDao;
import org.mansar.digitalbanking.exception.BalanceNotSufficientException;
import org.mansar.digitalbanking.exception.BankAccountNotFoundException;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.CurrentAccount;
import org.mansar.digitalbanking.model.Customer;
import org.mansar.digitalbanking.model.Operation;
import org.mansar.digitalbanking.model.OperationType;
import org.mansar.digitalbanking.model.SavingAccount;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements IBankService {
    private final BankAccountDao bankAccountDao;
    private final CustomerDao customerDao;
    private final OperationDao operationDao;

    @Override
    public CurrentAccount saveCurrentAccount(double initAmount, double overDraft, Long customerId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        BankAccount bankAccount = CurrentAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(initAmount)
                .customer(customer)
                .overDraft(overDraft)
                .build();

        return (CurrentAccount) bankAccountDao.save(bankAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(double initAmount, double interestRate, Long customerId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        BankAccount bankAccount = SavingAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(initAmount)
                .customer(customer)
                .interestRate(interestRate)
                .build();
        return (SavingAccount) bankAccountDao.save(bankAccount);
    }


    @Override
    @Transactional
    public void debit(String accountId, double amount, String description) {
        BankAccount bankAccount = getAccount(accountId);
        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException(accountId, amount);
        Operation operation = new Operation(amount, OperationType.DEBIT, bankAccount, description);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        operationDao.save(operation);
        bankAccountDao.save(bankAccount);
    }

    @Override
    @Transactional
    public void credit(String accountId, double amount, String description) {
        BankAccount bankAccount = getAccount(accountId);
        Operation operation = new Operation(amount, OperationType.CREDIT, bankAccount, description);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        operationDao.save(operation);
        bankAccountDao.save(bankAccount);

    }

    @Override
    @Transactional
    public void transfer(String accountIdSource, String accountIdDestination, double amount, String description) {
        debit(accountIdSource, amount, description);
        credit(accountIdDestination, amount, description);
    }

    @Override
    public List<BankAccount> getAccounts() {
        return null;
    }

    private BankAccount getAccount(String id) throws BankAccountNotFoundException {
        return this.bankAccountDao.findById(id).orElseThrow(() -> new BankAccountNotFoundException(id));
    }
}
