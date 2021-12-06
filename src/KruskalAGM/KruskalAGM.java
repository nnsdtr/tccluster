package KruskalAGM;

import Grafo.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;

public class KruskalAGM {
    private final LinkedList<Aresta> arestas;
    private final Grafo agm;
    private final int[] chefe;
    private final int numArestas;

    public KruskalAGM(Grafo qual) {
        this.arestas = qual.getArestas();
        assert this.arestas != null;
        Collections.sort(this.arestas);

        this.numArestas = this.arestas.size();

        this.agm = new Grafo(qual.getNumVertices());

        this.chefe = new int[qual.getNumVertices()];
        for (int i=0; i < qual.getNumVertices(); i++)
            this.chefe[i] = i;
    }

    public Grafo buildAGM() {
        while (this.arestas.size() < this.numArestas) {
            Aresta corrente = this.arestas.poll();
            if (corrente == null)
                throw new NullPointerException("Aresta inexistente.");

            if (buscarChefe(corrente.getU()) != buscarChefe(corrente.getV())) {
                this.unir(corrente);
            }
        }

        return this.agm;
    }

    private int buscarChefe(int u) {
        if (this.chefe[u] == u)
            return u;
        return buscarChefe(this.chefe[u]);
    }

    private void unir(@NotNull Aresta qual) {
        int chefeU = this.buscarChefe(qual.getU());
        int chefeV = this.buscarChefe(qual.getV());
        this.chefe[chefeU] = chefeV;
        this.agm.adicionarAresta(qual);
    }
}
