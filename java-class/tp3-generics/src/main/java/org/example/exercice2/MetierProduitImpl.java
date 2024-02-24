package org.example.exercice2;

import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IMetier<Produit> {
    private static int idGenerator = 1;
    private final List<Produit> produits =  new ArrayList<>();
    @Override
    public void add(Produit o) {
        o.setId(idGenerator);
        produits.add(o);
        idGenerator++;
    }

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public Produit findById(int id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        Produit produit = findById(id);
        if (produit != null) {
            produits.remove(produit);
        }
    }
}
