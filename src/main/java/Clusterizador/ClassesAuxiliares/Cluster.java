package Clusterizador.ClassesAuxiliares;

public class Cluster implements Comparable<Cluster>{

    private Ponto pontos[];
    private Ponto centroide;
    private int size;
    private Arvbin<Cluster> raiz;

    /* Complexidade O(1) */
    public Cluster(Ponto p){

        this.pontos = new Ponto[1];
        this.pontos[0] = p;
        this.centroide = p;
        this.size = 1;
        this.raiz = new Arvbin<Cluster>(this);
    }

    /* Complexidade O(n) */
    public Cluster(Cluster c1, Cluster c2){

        this.size = c1.size + c2.size;
        this.pontos = new Ponto[size];

        for (int i = 0; i < c1.size; i++)
            this.pontos[i] =   c1.pontos[i];  

        for (int i = c1.size, j = 0; j < c2.size; i++, j++) 
            this.pontos[i] = c2.pontos[j];

        calculaCentroide(); //O(n)

        this.raiz = new Arvbin<Cluster>(this, c1.raiz, c2.raiz);

    }

    /* Complexidade O(1) */
    public int getSize(){
        return size;
    }

    /* Complexidade O(1) */
    public Ponto getCentroide(){
        return centroide;
    }

    /* Complexidade O(1) */
    public String imprimeCentroide(){
        return centroide.toString();
    }

    /* Complexidade O(1) */
    public Arvbin<Cluster> getRaiz() {
        return raiz;
    }
    /* Complexidade O(1) */
    public String toString(){
        return centroide.toString();
    }

    // Complexidade O(n)
    public void calculaCentroide(){

        double somaX = 0;
        double somaY = 0;

        for (Ponto ponto : pontos){
            somaX += ponto.getX();
            somaY += ponto.getY();
        }

        double mediaX = somaX / size;
        double mediaY = somaY / size;

        this.centroide = new Ponto(mediaX, mediaY);
    }

    /* Complexidade O(1) */
    public int compareTo(Cluster c){

        if (this.centroide.getX() == c.centroide.getX() && this.centroide.getY() == c.centroide.getY())
            return 0;
        if (this.centroide.getX() > c.centroide.getX() && this.centroide.getY() > c.centroide.getY())
            return 1;
        else
            return -1;
    }
}
