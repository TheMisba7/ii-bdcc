package org.mansar.digitalbanking.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        this("Customer with id " + customerId + " doesn't exist");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
