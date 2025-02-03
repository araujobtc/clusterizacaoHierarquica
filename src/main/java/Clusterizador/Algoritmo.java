package Clusterizador;

import Clusterizador.ClassesAuxiliares.Cluster;
import Clusterizador.ClassesAuxiliares.ListaDuplamenteGenerica;
import Clusterizador.ClassesAuxiliares.Ponto;

public class Algoritmo {

    protected ListaDuplamenteGenerica<Cluster> clusters; /* Lista de clusters */

    protected int qtdPontos; /* Quantidade de pontos */

    public Algoritmo(int qtdPontos) {
        this.clusters = new ListaDuplamenteGenerica<Cluster>();
        this.qtdPontos = qtdPontos;
    }

    // Complexidade O(n)
    public void criaPontosClusters() {
        /* Todos os pontos são criados e transformados em clusters de um ponto */
        for (int i = 0; i < this.qtdPontos; i++)// O(n)
            this.clusters.insere(criaCluster());
    }

    // Complexidade O(1)
    public Cluster criaCluster() {
        /* Cria um ponto aleatório e transforma em cluster de um ponto */
        Ponto p = new Ponto(this.qtdPontos);

        /* Coloca o ponto num cluster */
        Cluster c = new Cluster(p);

        return c;
    }

    // Remove da lista de clusters, os clusters que deixarÃ£o de existir porque
    // serão fundidos, se tornando um novo cluster
    // Complexidade O(n)
    public void removeClusters(Cluster c1, Cluster c2) {
        // Remove os dois clusters com a menor distÃ¢ncia e junta em um novo
        this.clusters.remove(c1); // O(n)
        this.clusters.remove(c2); // O(n)
    }

    // Complexidade O(1)
    public ListaDuplamenteGenerica<Cluster> getClusters() {
        return this.clusters;
    }

    // Complexidade O(1)
    public int getQtdPontos() {
        return this.qtdPontos;
    }
}
