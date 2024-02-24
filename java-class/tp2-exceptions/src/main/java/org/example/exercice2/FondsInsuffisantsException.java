package org.example.exercice2;

public class FondsInsuffisantsException extends RuntimeException{
    public FondsInsuffisantsException(String insufficientFunds) {
        super(insufficientFunds);
    }
}
