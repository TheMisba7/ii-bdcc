package org.example;

public class Cercle extends Figure{
    private static final double PI = 3.14;
    private double rayon;

    public Cercle(String nom, double rayon) {
        super(nom);
        this.rayon = rayon;
    }

    @Override
    public double calculerAire() {
        return PI * Math.pow(this.rayon, 2);
    }

    @Override
    public double calculerPerimetre() {
        return 2 * PI * this.rayon;
    }
}
