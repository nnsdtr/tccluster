package Algoritmos;

import Grafo.*;
import java.util.*;

public class Cluster {
    Grafo agm;

    public Cluster(Grafo agm) {
        this.agm = agm;
    }

    public Grafo clusterizar(int k){
        ArrayList<Aresta> arestas = this.agm.getArestas();
        assert arestas != null;
        Collections.sort(arestas);

        for (int i = 0; i < k - 1; i++){
            Aresta removida = arestas.remove(arestas.size()-1);
            assert removida != null;
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
