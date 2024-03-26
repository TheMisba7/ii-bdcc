package org.example.exercice1;

public class Talkative implements Runnable {
    private Integer value;

    public Talkative(Integer value) {
        this.value = value;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 100; i++) {
            System.out.println(value);
        }
    }
}
