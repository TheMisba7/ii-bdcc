package org.example.exercice1;

public class NombreNegatifException extends Exception{
    private int val;

    public int getVal() {
        return val;
    }

    public NombreNegatifException(int val) {
        this.val = val;
    }
}
