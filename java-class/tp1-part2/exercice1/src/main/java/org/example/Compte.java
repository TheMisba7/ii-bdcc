package org.example;

public class Compte {
    public static int nbCompte;
    private long num;
    private double solde;

    public Compte() {
        nbCompte++;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    void retirer(double montant) {
        if (this.solde < montant)
            throw new RuntimeException("insufficient funds ");

        this.solde = this.solde - montant;
    }

    void deposer(double montant) {
        this.solde += montant;
    }

    public void afficherCompteInfo() {
        String detail = "Num: " + num  + " solde: " + solde;
        System.out.println(detail);
    }

    public static void afficherNbComptes(){
        System.out.println("created accounts: " + nbCompte);
    }
}
