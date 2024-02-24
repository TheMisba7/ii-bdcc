package org.example;

public abstract class Employe {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String tel;
    protected double salary;

    public abstract double calculerSalire();

    public Employe(String nom, String prenom, String email, String tel, double salary) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return " Nom:" + this.nom + ", Prenom: " + this.prenom + ", Salary: " + calculerSalire();
    }
}
