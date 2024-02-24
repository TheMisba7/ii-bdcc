package org.example;

public class Main {
    public static void main(String[] args) {
        Figure cercel = new Cercle("Nom1", 5);
        Figure rectangle = new Rectangle("recNom", 5, 8);

        cercel.afficherDetails();
        rectangle.afficherDetails();

    }
}