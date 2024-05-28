package org.mansar.digitalbanking.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String id) {
        super("Ban account not found, id ====> " + id);
    }
}
