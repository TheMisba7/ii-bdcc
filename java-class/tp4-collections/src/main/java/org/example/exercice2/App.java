package org.example.exercice2;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, Double> nameToNote = new HashMap<>();

        nameToNote.put("etu1", 14d);
        nameToNote.put("etu2", 18.2);
        nameToNote.put("etu3", 14.5);
        nameToNote.put("etu4", 20d);
        nameToNote.put("etu5", 13d);
        nameToNote.put("etu6", 16d);

        display(nameToNote);
        Double etu2 = nameToNote.get("etu2");
        etu2 += 6;
        nameToNote.put("etu2", etu2);
        nameToNote.put("etu1", nameToNote.get("etu1") + 2);

        display(nameToNote);

        nameToNote.remove("etu1");
        System.out.println("map size: " + nameToNote.size());
        display(nameToNote);
        displayStatistics(nameToNote);

        boolean anyMatch = nameToNote.values()
                .stream()
                .anyMatch(n -> n == 20);
        System.out.println( anyMatch ? " note 20 is present": "no note equals to 20 ");
    }

    private static void displayStatistics(Map<String, Double> nameToNote) {
        double max = 0;
        double min = -1;
        double sum = 0;
        for (Double note: nameToNote.values()) {
            sum += note;
            if (max < note ) {
                max = note;
            } else if (min > note || min == -1) {
                min = note;
            }
        }
        System.out.println("max: " + max + ", min:" + min
                + ", moyenne: " + sum / nameToNote.size());
    }

    private static void display(Map<String, Double> nameToNote) {
        nameToNote
                .forEach((key, value) -> System.out.println("etudiant: "
                + key + ", note: " + value));
    }
}
