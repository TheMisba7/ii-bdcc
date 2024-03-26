package org.example.exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercice2Main {
    public static void main(String[] args) throws InterruptedException {
        Integer [] values = new Integer[10000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Sommeur> sommeurs = new ArrayList<>();
        for (int i = 0; i < 10000; i += 1000) {
            sommeurs.add(new Sommeur(values, i, i + 1000));
        }
        sommeurs.forEach(executor::execute);

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        int total = sommeurs.stream().mapToInt(Sommeur::getSomme).sum();
        System.out.println("total is: " + total);
    }
}
