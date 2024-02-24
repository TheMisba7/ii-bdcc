package org.example;

public class Commande {
    private double amount;
    private Paiement paymentProvider;

    public Commande(double amount, Paiement paymentProvider) {
        this.amount = amount;
        this.paymentProvider = paymentProvider;
    }

    public void processPayment() {
        paymentProvider.effectuerPaiement(amount);
    }
}
