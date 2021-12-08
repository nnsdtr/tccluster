package Algoritmos;

import Grafo.*;
import java.util.*;

public class Cluster {
    private final Grafo grafo;
    private Grafo agm;
    private ArrayList<ArrayList<Vertice>> clusteres;

    public Cluster(Grafo grafo) {
        this.grafo = grafo;
        this.agm = null;
    }

    public void build(int k){
        Kruskal kruskal = new Kruskal(this.grafo);
        this.agm = kruskal.buildAGM();

        ArrayList<Aresta> arestas = this.agm.getArestas();
        assert arestas != null;
        Collections.sort(arestas);

        for (int i = 0; i < k - 1; i++){
            Aresta removida = arestas.remove(arestas.size()-1);
            assert removida != null;
            this.agm.removerAresta(removida);
        }

        DFS busca = new DFS(this.agm);
        this.clusteres = busca.buscaEmProfundidade();
    }

    public Grafo getAgm() {
        return agm;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (ArrayList<Vertice> clusterAtual : this.clusteres) {
            builder.append("Grupo de pesquisa ").append(i).append(":\n");
            Collections.sort(clusterAtual);
            for (Vertice aluno : clusterAtual)
                builder.append(aluno).append("\n");
            builder.append("\n");
            i++;
        }
        return builder.toString();
    }
}
