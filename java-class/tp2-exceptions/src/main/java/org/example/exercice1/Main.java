package org.example.exercice1;

public class Main {
    public static void main(String[] args) {
        try {
            EntierNaturel entierNaturel = new EntierNaturel(-1);
        } catch (NombreNegatifException e) {
            System.out.println("negative value: " + e.getVal());
        }
    }
}