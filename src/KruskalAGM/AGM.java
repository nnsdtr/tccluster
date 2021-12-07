package KruskalAGM;

import Grafo.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;

public class AGM {
    private final LinkedList<Aresta> arestas;
    private final Grafo agm;
    private final int[] chefe;
    private final int numVertices;
    private int numArestasAdicionadas;

    public AGM(@NotNull Grafo qual) {
        this.arestas = qual.getArestas();
        assert this.arestas != null;
        Collections.sort(this.arestas);

        this.numVertices = qual.getNumVertices();

        this.agm = new Grafo(qual.getNumVertices());
        this.numArestasAdicionadas = 0;

        this.chefe = new int[qual.getNumVertices()];
        for (int i=0; i < qual.getNumVertices(); i++)
            this.chefe[i] = i;
    }

    public Grafo build() {
        while (this.numArestasAdicionadas < this.numVertices - 1) {
            Aresta corrente = this.arestas.poll();
            if (corrente == null)
                throw new NullPointerException("Aresta inexistente.");

            int chefeU = buscarChefe(corrente.getU());
            int chefeV = buscarChefe(corrente.getV());

            if (chefeU != chefeV) {
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
        this.numArestasAdicionadas++;
    }
}
