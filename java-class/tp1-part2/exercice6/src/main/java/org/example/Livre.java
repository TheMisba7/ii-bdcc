package org.example;

public class Livre implements Empruntable{
    private String titre;
    private String auteur;
    private boolean isEmprunter;
    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    @Override
    public void emprunter() {
        this.isEmprunter = true;
    }

    @Override
    public void retourner() {
        this.isEmprunter = false;
    }
}
