package Clusterizador;

import Clusterizador.ClassesAuxiliares.Cluster;
import Clusterizador.ClassesAuxiliares.Distancia;

public class Naive extends Algoritmo {

    /* Construtor */
    public Naive(int qtdPontos) {
        super(qtdPontos);
        criaPontosClusters(); // O(n)
    }

    // Calcula as distancias entre clusters da lista e as compara para obter a menor distancia
    // Complexidade O(n²)
    public Distancia calculaMenorDistancia() {
        /* Cria uma distancia cujo o valor é ______ */
        Distancia distancia_aux = new Distancia(Double.MAX_VALUE);

        // Pega dois clusters, calcula a distancia entre eles, e compara com a distancia auxiliar até encontrar a menor
        for (int i = 0; i < clusters.getTam() - 1; i++) {
            for (int j = i + 1; j < clusters.getTam(); j++) {
                Distancia distancia = new Distancia(clusters.busca(i), clusters.busca(j));

                if (distancia.getDistancia() < distancia_aux.getDistancia()) {
                    distancia_aux = distancia;
                }
            }
        }
        return distancia_aux;
    }

    /* Realiza a clusterização usando uma abordagem naïve */
    // Complexidade O(n³)
    public void clusteriza() {
        // Clusteriza até restar apenas um cluster
        while (clusters.getTam() > 1) {
            // Pega a menor distância e cria um novo cluster
            Distancia menorDistancia = calculaMenorDistancia(); // O(n²)
            Cluster novoCluster = new Cluster(menorDistancia.getC1(), menorDistancia.getC2()); // O(n)

            removeClusters(menorDistancia.getC1(), menorDistancia.getC2()); // O(n) - Remove os clusters antigos

            clusters.insere(novoCluster); // O(1) - Adiciona o novo cluster na lista
        }
    }
}
