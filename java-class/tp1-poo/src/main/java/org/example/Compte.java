package org.example;

public class Compte {
    private long num;
    private String nomClient;
    private double solde;

    public Compte() {
    }

    public Compte(long num, String nomClient, double solde) {
        this.num = num;
        this.nomClient = nomClient;
        this.solde = solde;
    }

    public void afficherCompteInfo() {
        String detail = "Num: " + num + " Client: " + nomClient + " solde: " + solde;
        System.out.println(detail);
    }

    void retirer(double montant) {
        if (this.solde < montant)
            throw new RuntimeException("insufficient funds ");

        this.solde = this.solde - montant;
    }

    void deposer(double montant) {
        this.solde += montant;
    }
    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
