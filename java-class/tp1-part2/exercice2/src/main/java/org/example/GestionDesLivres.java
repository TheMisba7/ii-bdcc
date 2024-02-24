package org.example;

public class GestionDesLivres {
    public static void main(String[] args) {
        Adherent adherent = new Adherent("Mansar",
                "Abdeddaim", "amansar@gmail.com", "03987362", 23, 99827);

        Auteur auteur = new Auteur("Fyodor", "Dostoevsky", "dostoevsky@yandex.ru", "098373423", 56);

        Livre livre = new Livre(99834934, "Crime and Punichment", auteur);

        System.out.println(adherent.toString());
        System.out.println(livre.toString());
    }
}
