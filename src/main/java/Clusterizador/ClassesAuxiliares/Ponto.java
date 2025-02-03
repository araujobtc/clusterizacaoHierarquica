package Clusterizador.ClassesAuxiliares;

import java.util.Random;

public class Ponto {

    private double x;
    private double y;

    /* Complexidade O(1) */
    public Ponto(int limite){
        Random rand = new Random();
        this.x = rand.nextInt(limite) + 1;
        this.y = rand.nextInt(limite) + 1;
    }

    /* Complexidade O(1) */
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
    }

    /* Complexidade O(1) */
    public double getX(){
        return x;
    }

    /* Complexidade O(1) */
    public double getY(){
        return y;
    }

    /* Complexidade O(1) */
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
