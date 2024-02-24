package org.example;

public class Auteur extends Personne{
    private int numAuteur;

    public Auteur(String nom, String prenom, String email, String tel, int age) {
        super(nom, prenom, email, tel, age);
    }

    @Override
    public String toString() {
        return "Num Auteur: " + this.numAuteur + super.toString();
    }
}
