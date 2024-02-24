package org.example;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void emprunterObjet(Empruntable objet) {
        objet.emprunter();
    }
}
