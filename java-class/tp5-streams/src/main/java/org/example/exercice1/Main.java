package org.example.exercice1;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static void operate(List<String> content) {
        Stream<String> stringStream = content.stream()
                .filter(m -> m.contains("a"))
                .filter(m -> m.length() > 3)
                .map(m -> new StringBuilder(m).reverse().toString());

        Stream<char[]> motToChar =
                stringStream.
                filter(m -> m.contains("e"))
                .map(String::toCharArray);

        Stream<String> upperCaseStream =
                content.stream()
                        .map(String::toUpperCase);

        Stream<Integer> motToLength =
                content.stream()
                .map(String::length);

        List<String> stringList =
                content.stream()
                .map(m -> m + "-" + content.indexOf(m))
                .toList();
        Stream<String> motIndex = IntStream.range(0, content.size())
                .mapToObj(i -> content.get(i) + "-" + i);
    }
}