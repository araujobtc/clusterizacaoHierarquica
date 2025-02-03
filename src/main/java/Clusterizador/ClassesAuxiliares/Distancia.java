package Clusterizador.ClassesAuxiliares;

public class Distancia implements Comparable<Distancia>{

    private Cluster c1, c2;
    private double distancia;

    /* Complexidade O(1) */
    public Distancia(Cluster c1, Cluster c2){
        this.c1 = c1;
        this.c2 = c2;
        this.distancia = calculaDistancia();
    }

    /* Complexidade O(1) */
    public Distancia(double distancia){
        this.c1 = null;
        this.c2 = null;
        this.distancia = distancia;
    }

    /* Complexidade O(1) */
    public double calculaDistancia(){
        return Math.sqrt
        ( 
            Math.pow(c1.getCentroide().getX() - c2.getCentroide().getX(), 2) 
            +  
            Math.pow(c1.getCentroide().getY() - c2.getCentroide().getY(), 2)    
        );    
    }

    /* Complexidade O(1) */
    public double getDistancia() {
        return distancia;
    }

    /* Complexidade O(1) */
    public Cluster getC1(){
        return this.c1;
    }

    /* Complexidade O(1) */
    public Cluster getC2(){
        return this.c2;
    }

    /* Complexidade O(1) */
    public int compareTo(Distancia d) {
        if (distancia == d.distancia)
            return 0;
        else if (distancia > d.distancia)
            return 1;
        else
            return -1;
    }
}
