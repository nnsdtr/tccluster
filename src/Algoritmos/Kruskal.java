package Algoritmos;

import Grafo.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
    private final ArrayList<Aresta> arestas;
    private final Grafo agm;
    private final int[] chefe;
    private final int numVertices;
    private int numArestasAdicionadas;

    public Kruskal(@NotNull Grafo qual) {
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

    public Grafo buildAGM() {
        while (this.numArestasAdicionadas < this.numVertices - 1) {
            Aresta atual = this.arestas.remove(arestas.size()-1);
            if (atual == null)
                throw new NullPointerException("Aresta inexistente.");

            int chefeU = buscarChefe(atual.u().getID());
            int chefeV = buscarChefe(atual.v().getID());

            if (chefeU != chefeV) {
                this.unir(atual);
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
        int chefeU = this.buscarChefe(qual.u().getID());
        int chefeV = this.buscarChefe(qual.v().getID());
        this.chefe[chefeU] = chefeV;
        this.agm.adicionarAresta(qual);
        this.numArestasAdicionadas++;
    }
}
