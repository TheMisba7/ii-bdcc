package org.example;

public class PayPal extends Paiement{
    private String email;

    @Override
    public void effectuerPaiement(double montant) {
        System.out.println("A Transaction is going through PayPal service");
    }
}
