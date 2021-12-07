package Algoritmos;

import Grafo.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class DFS {
    private final Grafo floresta;
    private final ArrayList<ArrayList<Vertice>> clusteres;
    private int numComponentes;
    private int timestamp;

    public DFS(Grafo floresta) {
        this.floresta = floresta;
        this.clusteres = new ArrayList<>();
        this.clusteres.add(new ArrayList<>());
        this.numComponentes = 1;
        this.timestamp = 0;
    }

    public ArrayList<ArrayList<Vertice>> buscaEmProfundidade() {
        for (int i = 0; i < floresta.getListaAdjacencia().length; i++) {
            ArrayList<Aresta>[] lista = floresta.getListaAdjacencia();
            if (lista[i].size() > 0) {
                Vertice u = lista[i].get(0).getVertice(i);
                if (u.getCor() == Cor.BRANCO) {
                    visitar(u);
                    this.numComponentes++;
                    this.clusteres.add(new ArrayList<>());
                }
            }
        }
        return this.clusteres;
    }

    private void visitar(@NotNull Vertice u) {
        this.timestamp++;
        u.setDescoberta(this.timestamp);
        u.setCor(Cor.CINZA);
        this.setComponente(u, this.numComponentes);

        ArrayList<Vertice> adjacentesDeU = this.floresta.getAdjacentes(u);

        if (adjacentesDeU == null)
            throw new NullPointerException("Lista de adjacentes é nula.");

        for (Vertice adjacente : adjacentesDeU) {
            if (adjacente.getCor() == Cor.BRANCO) {
                adjacente.setPai(u);
                visitar(adjacente);
            }
        }
        u.setCor(Cor.PRETO);
        this.timestamp++;
        u.setTermino(this.timestamp);
    }

    private void setComponente(Vertice u, int componente) {
        u.setComponente(componente);
        ArrayList<Vertice> clusterAtual = this.clusteres.get(componente-1);
        if (clusterAtual.contains(u))
            System.out.println("aqui");
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
