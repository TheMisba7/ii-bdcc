package org.example.exercice3;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Set<String> groupA = new HashSet<>();
        Set<String> groupB = new HashSet<>();

        groupA.add("Ahmed");
        groupA.add("Abdeddaim");
        groupA.add("Said");
        groupA.add("Hamid");
        groupA.add("Karim");

        groupB.add("Mustafa");
        groupB.add("Reda");
        groupB.add("Youssef");
        groupB.add("Ayoub");
        groupB.add("Karim");

        Set<String> intersection = new HashSet<>(groupA);
        intersection.retainAll(groupB);

        System.out.println("*** Intersection of group A and B ***");
        display(intersection);

        Set<String> union = new HashSet<>(groupA);
        union.addAll(groupB);
        System.out.println("*** Union of group A and B ***");
        display(union);
    }

    private static void display(Set<String> content) {
        content.forEach(System.out::println);
    }
}
