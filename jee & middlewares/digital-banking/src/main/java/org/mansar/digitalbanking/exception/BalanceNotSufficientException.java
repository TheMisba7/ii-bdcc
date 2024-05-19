package org.mansar.digitalbanking.exception;

public class BalanceNotSufficientException extends RuntimeException {
    private final String accountId;
    private final double amount;
    public BalanceNotSufficientException(String accountId, double amount) {
        super("Balance not sufficient (amount to debit " + amount + ") for account " + accountId);
        this.accountId = accountId;
        this.amount = amount;
    }
}
