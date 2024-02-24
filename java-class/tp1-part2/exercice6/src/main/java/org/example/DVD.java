package org.example;

public class DVD implements Empruntable{
    private String titre;
    private String realisateur;
    private boolean isEmprunter;

    public DVD(String titre, String realisateur) {
        this.titre = titre;
        this.realisateur = realisateur;
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
