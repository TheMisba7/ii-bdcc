package org.example.exercice2;

import java.util.Scanner;

public class App {
    private static final IMetier<Produit> service = new MetierProduitImpl();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean exist = false;
        while (!exist) {
            System.out.println("Please choose one of the options below: ");
            System.out.println("1. Afficher la liste des produits");
            System.out.println("2. Rechercher un produit par son id.");
            System.out.println("3. Ajouter un nouveau produit dans la liste.");
            System.out.println("4. Supprimer un produit par id.");
            System.out.println("5. Quitter ce programme.");
            int nbr = scanner.nextInt();
            switch (nbr) {
                case 1 -> {
                    for (Produit p: service.getAll()) {
                        System.out.println(p.toString());
                    }
                }
                case 2 -> recherche();
                case 3 -> newProduct();
                case 4 -> delete();
                case 5 -> exist = true;
            }
        }
        System.out.println("Bye.");
    }

    public static void newProduct() {
        Produit produit = new Produit();
        System.out.println("Product name: ");
        produit.setNom(scanner.next());
        System.out.println("Product's marque: ");
        produit.setMarque(scanner.next());
        System.out.println("Price: ");
        produit.setPrix(scanner.nextDouble());
        System.out.println("Items in stock: ");
        produit.setItemsInStock(scanner.nextInt());
        service.add(produit);
    }

    private static void recherche() {
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        Produit byId = service.findById(id);
        if (byId == null) {
            System.out.println("No product found with id: " + id);
            return;
        }
        System.out.println(byId.toString());
    }

    private static void delete() {
        System.out.println("Enter product's id to delete: ");
        int id = scanner.nextInt();

        service.delete(id);
    }
}
