package org.example.exercice2;

public class Sommeur implements Runnable{
    private final Integer [] values;
    private final int start;
    private final int end;
    private int sum = 0;

    public Sommeur(Integer[] values, int start, int end) {
        this.values = values;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            this.sum += values[i];
        }
    }

    public int getSomme() {
        return sum;
    }
}
