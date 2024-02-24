package org.example;

public abstract class Figure {
    private String nom;

    public Figure(String nom) {
        this.nom = nom;
    }

    public abstract double calculerAire();
    public abstract double calculerPerimetre();
    public void afficherDetails() {
        System.out.println(
                "Nom: " + this.nom
                + ", Aire: " + calculerAire()
                + ", Périmètre: " + calculerPerimetre()
        );
    }


}
