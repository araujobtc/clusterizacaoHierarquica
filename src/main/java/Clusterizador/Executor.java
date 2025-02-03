package Clusterizador;

import Clusterizador.ClassesAuxiliares.ListaDuplamenteGenerica;
import Clusterizador.ClassesAuxiliares.Cluster;

public class Executor {

    private long QTDEXECUCOES = 10;

    private long tempoInicio, tempoFim, duracaoMedia;

    public Executor() {
        //
    }

    public void executa() {
        System.out.println("Execução iniciada.");

        int[] nums = { 10, 20/*, 30, 40, 50, 100, 200, 500, 1000, 5000, 10000, 20000, 50000, 100000 */};

        for (int n : nums) {
            System.out.println(n + " pontos");
            executaFilaPrioridade(n);
            executaNaive(n);
        }

        System.out.println("Fim da execução.");
    }

    public void executaFilaPrioridade(int n) {
        tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < QTDEXECUCOES; i++) {
            FilaPrioridade heap = new FilaPrioridade(n);// O(n²)
            heap.clusteriza(); // O(n log n)
            // mostraArvore(heap.getClusters());
        }

        tempoFim = System.currentTimeMillis();
        duracaoMedia = (tempoFim - tempoInicio) / QTDEXECUCOES;

        System.out.println("\nHEAP - Tempo médio de execução: " + duracaoMedia + " ms\n");
    }

    public void executaNaive(int n) {
        tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < QTDEXECUCOES; i++) {
            Naive naive = new Naive(n); // O(n)
            naive.clusteriza(); // O(n³)
            // mostraArvore(naive.getClusters());
        }

        tempoFim = System.currentTimeMillis();
        duracaoMedia = (tempoFim - tempoInicio) / QTDEXECUCOES;

        System.out.println("\nNAIVE - Tempo médio de execução: " + duracaoMedia + " ms\n");
    }

    public void mostraArvore(ListaDuplamenteGenerica<Cluster> clusters) {
        Cluster ultCluster = clusters.getDadoPrim();

        ultCluster.getRaiz().desenhaArvore();
    }
}
