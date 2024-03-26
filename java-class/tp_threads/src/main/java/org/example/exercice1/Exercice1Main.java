package org.example.exercice1;

import java.util.ArrayList;
import java.util.List;

public class Exercice1Main {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            threads.add(new Thread(new Talkative(i)));
        }
        threads.forEach(Thread::start);
        //6): Que constatez-vous: concurrent execution of the threads
    }
}
