package org.example.exercice2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        double toFilter = 5000;
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("name1", "IT", 8736));
        employees.add(new Employee("name2", "IT", 8736));
        employees.add(new Employee("name3", "IT", 8736));
        employees.add(new Employee("name4", "IT", 8736));
        employees.add(new Employee("name5", "IT", 8736));
        employees.add(new Employee("name6", "IT", 8736));
        employees.add(new Employee("name7", "IT", 8736));
        employees.add(new Employee("name8", "IT", 8736));
        employees.add(new Employee("name9", "IT", 8736));

        // sum salary
        double sum = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        Stream<Employee> sorted = employees
                .stream()
                .sorted(Comparator.comparing(Employee::getName));

        Optional<Employee> minSalary = employees.stream()
                .min(Comparator.comparing(Employee::getSalary));

        List<Employee> employees1 = employees.stream()
                .filter(e -> e.getSalary() > toFilter)
                .toList();

        Employee highestSalary = employees.stream()
                .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
                .get();

        String names = employees.stream()
                .map(Employee::getName)
                .reduce("", (e1, e2) -> e1 + "-" + e2);
        System.out.println(names);
    }
}
