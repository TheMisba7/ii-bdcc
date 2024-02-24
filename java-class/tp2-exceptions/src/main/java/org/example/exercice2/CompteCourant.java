package org.example.exercice2;

public class CompteCourant extends CompteBancaire{

    public CompteCourant(int num, double balance, String holder) {
        super(num, balance, holder);
    }

    @Override
    void retirer(double montant) {
        balance -= montant;
    }
}
