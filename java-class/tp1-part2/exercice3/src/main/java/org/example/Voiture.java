package org.example;

public class Voiture extends Vehicule{
    private String model;
    private int annee;

    public Voiture(String nom, double prix, String model, int annee) {
        super(nom, prix);
        this.model = model;
        this.annee = annee;
    }

    @Override
    public void emettreSon() {
        System.out.println("La voiture vrombit.");
    }
}
