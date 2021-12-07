package Grafo;

import java.util.Collections;
import java.util.LinkedList;

public class Cluster {
    Grafo agm;

    public Cluster(Grafo agm) {
        this.agm = agm;
    }

    public Grafo Clusterizacao (int k){
        int numArestas = k-1;

        LinkedList<Aresta> arestas = this.agm.getArestas();
        Collections.sort(arestas);

        for (int i = 0; i < k; i++){
            Aresta removida = arestas.pollLast();
            this.agm.removerAresta(removida);
        }
        return this.agm;
    }

    public Grafo getAgm() {
        return agm;
    }

    public void setAgm(Grafo agm) {
        this.agm = agm;
    }
}
