package org.example;

public class GestionBibliothequeApp {
    public static void main(String[] args) {
        Empruntable livre = new Livre("titre", "auteur");
        Empruntable dvd = new DVD("dvd1", "realisateur");

        User user = new User("User");

        user.emprunterObjet(livre);
        user.emprunterObjet(dvd);

        dvd.retourner();
    }
}
