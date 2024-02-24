package org.example;

public class Manager extends Employe{
    public Manager(String nom, String prenom, String email, String tel, double salary) {
        super(nom, prenom, email, tel, salary);
    }

    @Override
    public double calculerSalire() {
        return salary + salary * 0.20;
    }
}
