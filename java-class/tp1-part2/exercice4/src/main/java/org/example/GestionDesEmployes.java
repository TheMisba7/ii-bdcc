package org.example;

public class GestionDesEmployes {
    public static void main(String[] args) {
        Employe imgenieur = new Ingénieur("Mansar", "Abdeddaim",
                "amansar@gmail.com", "+212 039943", 20000);
        Employe manager = new Manager("testManager", "testManager",
                "manager@gmail.com", "+212 03343", 30000);


        System.out.println(
                "spécialité: " + getRole(imgenieur)
                + imgenieur.toString()
        );

        System.out.println(
                "spécialité: " + getRole(manager)
                        + manager.toString()
        );
    }

    public static String getRole(Employe employe) {
        if (employe instanceof Ingénieur)
            return "Ingénieur";
        else if (employe instanceof Manager)
            return "Manager";
        else
            return "Unknown";
    }
}
