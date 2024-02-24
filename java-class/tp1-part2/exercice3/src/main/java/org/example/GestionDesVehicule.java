package org.example;

public class GestionDesVehicule {
    public static void main(String[] args) {
        Moto moto = new Moto("motoName", 20000, "MotoMarque", 20);
        Avion avion = new Avion("AvionNAme", 9278392, "AvionCompagnie", 400);
        Voiture voiture = new Voiture("voitureName", 38499, "model-xXX", 2019);

        moto.afficherInformations();
        moto.emettreSon();

        avion.afficherInformations();
        avion.emettreSon();

        voiture.afficherInformations();
        voiture.emettreSon();
    }
}
