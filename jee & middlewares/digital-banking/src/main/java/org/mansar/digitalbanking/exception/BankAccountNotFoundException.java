package org.mansar.digitalbanking.exception;

public class BankAccountNotFoundException extends RuntimeException {
    private int code;
    private String message;
    public BankAccountNotFoundException(String id) {
        super("Ban account not found, id ====> " + id);
    }

    public BankAccountNotFoundException(String message, int code) {
        this.code = code;
        this.message = message;
    }

}
