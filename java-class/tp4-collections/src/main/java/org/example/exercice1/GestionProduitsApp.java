package org.example.exercice1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionProduitsApp {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Produit> produits = new ArrayList<>();
        // add products
        produits.add(new Produit(1, "p1", 123));
        produits.add(new Produit(2, "p2", 12));
        produits.add(new Produit(3, "p3", 13));
        produits.add(new Produit(4, "p4", 43));
        produits.add(new Produit(5, "p5", 24));
        produits.add(new Produit(6, "p6", 145));

        //remove
        produits.remove(2);
        produits.remove(3);
        produits.remove(1);
        // display
        produits
                .forEach(p -> System.out.println("name: " + p.getNom()));


        // modify
        produits.get(0).setNom("new name");
        produits
                .forEach(p -> System.out.println("name: " + p.getNom()));

        while (true) {
            System.out.println("Enter produit name: ");
            String name = scanner.next();
            List<Produit> list = produits.stream()
                    .filter(p -> p.getNom().contains(name))
                    .toList();
            if (list.isEmpty()) {
                System.out.println("no product with name: " + name);
            } else {
                System.out.println("*** Result ***");
                list
                        .forEach(p -> System.out.println("name: " + p.getNom()));
            }
            break;
        }

        System.out.println("Bye.");
    }
}
