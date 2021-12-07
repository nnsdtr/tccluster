package Algoritmos;

import Grafo.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class DFS {
    private final Grafo floresta;
    private final ArrayList<ArrayList<Vertice>> clusteres;
    private int numComponentes;

    public DFS(Grafo floresta) {
        this.floresta = floresta;
        this.clusteres = new ArrayList<>();
        this.clusteres.add(new ArrayList<>());
        this.numComponentes = 1;
    }

    public ArrayList<ArrayList<Vertice>> buscaEmProfundidade() {
        for (int i = 0; i < floresta.getNumVertices(); i++) {
            Vertice u = floresta.buscarVertice(i);
            assert u != null;

            if (u.getCor() == Cor.BRANCO) {
                visitar(u);
                this.numComponentes++;
            }
        }
        return this.clusteres;
    }

    private void visitar(@NotNull Vertice u) {
        u.setCor(Cor.CINZA);
        this.setComponente(u, this.numComponentes);
        ArrayList<Vertice> adjacentesDeU = this.floresta.getAdjacentes(u);
        if (adjacentesDeU == null)
            throw new NullPointerException("Lista de adjacentes é nula.");
        for (Vertice adjacente : adjacentesDeU) {
            if (adjacente.getCor() == Cor.BRANCO) {
                visitar(adjacente);
            }
        }
        u.setCor(Cor.PRETO);
    }

    private void setComponente(Vertice u, int componente) {
        if (componente > this.clusteres.size())
            this.clusteres.add(new ArrayList<>());

        ArrayList<Vertice> clusterAtual = this.clusteres.get(componente-1);
        clusterAtual.add(u);
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
