package org.example.exercice2;

public class CompteInexistantException extends RuntimeException{
    private int num;

    public CompteInexistantException(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
