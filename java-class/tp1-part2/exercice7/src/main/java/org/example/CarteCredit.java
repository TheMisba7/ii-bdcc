package org.example;

public class CarteCredit extends Paiement{
    private long creditCard;

    @Override
    public void effectuerPaiement(double montant) {
        System.out.println("A transaction is going through credit carte service");
    }
}
