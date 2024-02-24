package org.example.exercice2;

public class CompteEpargne extends CompteBancaire{
    public CompteEpargne(int num, double balance, String holder) {
        super(num, balance, holder);
    }

    @Override
    void deposer(double montant) {
        balance = montant + 0.029;
    }
}
