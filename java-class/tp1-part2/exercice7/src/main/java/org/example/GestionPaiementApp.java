package org.example;

public class GestionPaiementApp {
    public static void main(String[] args) {
        Commande order1 = new Commande(555, new CarteCredit());
        Commande order2 = new Commande(8474, new PayPal());

        order2.processPayment();
        order1.processPayment();
    }
}
