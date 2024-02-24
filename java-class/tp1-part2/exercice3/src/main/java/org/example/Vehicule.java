package org.example;

public class Vehicule {
    private String nom;
    private double prix;

    public Vehicule(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public void emettreSon() {
        System.out.println("this is not a sound but it should be");
    }

    public void afficherInformations() {
        System.out.println(
                "Nom:" + this.nom
                + ", Prix: " + this.nom);
    }
}
