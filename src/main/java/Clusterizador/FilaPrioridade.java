package Clusterizador;

import Clusterizador.ClassesAuxiliares.Cluster;
import Clusterizador.ClassesAuxiliares.Distancia;
import Clusterizador.ClassesAuxiliares.HeapBinariaMinima;
import Clusterizador.ClassesAuxiliares.ListaDuplamenteGenerica;

public class FilaPrioridade extends Algoritmo{
    HeapBinariaMinima distancias; /* Heap Mínima de distâncias */

    /* Complexidade O(n²) */
    public FilaPrioridade(int qtdPontos) {
        super(qtdPontos);
        criaPontosClusters(); // O(n)
        this.distancias = calculaDistancia(clusters);// O(n²) - Criando a heap mínima de distâncias      
    }

    /* Complexidade O(n²) */
    public HeapBinariaMinima calculaDistancia(ListaDuplamenteGenerica<Cluster> clusters) {
        // Esta fórmula é utilizada porque iremos calcular as distâncias do primeiro
        // elemento para todos, depois do segundo para todos menos o primeiro...
        int qtdDistancias = this.qtdPontos * (this.qtdPontos - 1) / 2; // Quantidades de distâncias calculadas

        Distancia[] vetDistancias = new Distancia[qtdDistancias]; // Cria um vetor do tamanho da quantidade de distâncias
        int indice = 0;

        // Calcula as distâncias do primeiro elemento para todos, depois do segundo para
        // todos menos o primeiro...
        for (int i = 0; i < clusters.getTam() - 1; i++)
            for (int j = i + 1; j < clusters.getTam(); j++, indice++)
                vetDistancias[indice] = new Distancia(clusters.busca(i), clusters.busca(j)); // O(1)

        /* No final, retorna uma heap mínima para conseguir a menor distancia em O(1) */
        return new HeapBinariaMinima(qtdDistancias, qtdDistancias, vetDistancias); // O(n)
    }

    /* Complexidade O(n) */
    public HeapBinariaMinima removeDistancias(HeapBinariaMinima distancias, Distancia menorDistancia) {
        // Cria um vetor e percorre o vetor original
        Distancia[] vetorDistancias = distancias.getVetor();
        Distancia[] vetDistancias = new Distancia[distancias.getN()];
        int indice = 0;

        for (int i = 1; i <= distancias.getN(); i++){
            // Se a distancia tiver um dos clusters da menor distância, não adiciona no vetor
            if ((vetorDistancias[i].getC1().equals(menorDistancia.getC1()) || vetorDistancias[i].getC1().equals(menorDistancia.getC2()))
            || (vetorDistancias[i].getC2().equals(menorDistancia.getC1()) || vetorDistancias[i].getC2().equals(menorDistancia.getC2())))
                continue;
            else
                vetDistancias[indice++] = vetorDistancias[i];
        }

        if (indice == 0)
            return new HeapBinariaMinima(1);
        else
            /* Retorna uma nova heap sem as distancias dos clusters de menor valor */
            return new HeapBinariaMinima(distancias.getN(), indice, vetDistancias);
    }

    /* Complexidade O(n log n) */
    public void clusteriza() {
        clusterizaRecursivo(); // O(n log n)
    }

    /* Complexidade O(n log n) */
    private void clusterizaRecursivo() {
        if ( clusters.getTam() <= 1) // Clusteriza até ter apenas um cluster
            return;

        // Pega os clusters com a menor distância e transforma num novo cluster
        Distancia menorDistancia = distancias.min(); // O(1)
        Cluster novoCluster = new Cluster(menorDistancia.getC1(), menorDistancia.getC2()); // O(n)

        removeClusters(menorDistancia.getC1(), menorDistancia.getC2()); // O(n) - Remove da lista os dois clusters com a menor distância

        distancias = removeDistancias(distancias, menorDistancia); // O(n) - Remove as distâncias dos clusters antigos

        // Calcula a distância desse novo cluster com o resto e adiciona na lista de distâncias
        for (int i = 0; i < clusters.getTam(); i++) // O(n log n)
            distancias.insere(new Distancia(novoCluster, clusters.busca(i)));

        // Adiciona o novo cluster na lista de clusters
        clusters.insere(novoCluster); // O(1)

        // Chama recursivamente o método para continuar o processo de clusterização
        clusterizaRecursivo();
    }
}
