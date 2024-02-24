package org.example;

public class GestionComptesApp {
    public static void main(String[] args) {
        Compte c1 = new Compte();
        Compte c2 = new Compte();

        Compte.afficherNbComptes();

        c1.setNum(111111111);
        c1.setSolde(303030);

        c1.retirer(1000);
        c1.afficherCompteInfo();

        c2.setNum(223222390);
        c2.setSolde(9838482);

        c2.afficherCompteInfo();
    }
}