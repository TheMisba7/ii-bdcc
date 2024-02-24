package org.example;

public class Ingénieur extends Employe{
    public Ingénieur(String nom, String prenom, String email, String tel, double salary) {
        super(nom, prenom, email, tel, salary);
    }

    @Override
    public double calculerSalire() {
        return salary + super.salary * 0.15;
    }
}
