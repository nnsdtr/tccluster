package Grafo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Grafo {
    private final int numVertices;
    private final ArrayList<Aresta> arestas;
    private final ArrayList<Aresta>[] listaAdjacencia;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.arestas = new ArrayList<>();
        this.listaAdjacencia = new ArrayList[numVertices];
        for (int v = 0; v < numVertices; v++)
            listaAdjacencia[v] = new ArrayList<>();
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public void adicionarAresta(int u, int v, int peso) {
        try {
            Aresta nova = new Aresta(u, v, peso);
            this.listaAdjacencia[u].add(nova);
            this.listaAdjacencia[v].add(nova);
            this.arestas.add(nova);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void adicionarAresta(@NotNull Aresta qual) {
        try {
            this.listaAdjacencia[qual.u()].add(qual);
            this.listaAdjacencia[qual.v()].add(qual);
            this.arestas.add(qual);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(@NotNull Aresta qual) {
        try {
            this.listaAdjacencia[qual.u()].remove(qual);
            this.listaAdjacencia[qual.v()].remove(qual);
            this.arestas.remove(qual);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public @Nullable ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    public String toString() {
        if (this.arestas != null) {
            StringBuilder build = new StringBuilder();
            for (Aresta e : arestas)
                build.append(e.u()).append("-").append(e.v()).append(" ");
            return build.toString();
        }

        return "Grafo desconexo.";
    }

    public String outputMatrizAdj() {
        if (this.arestas != null) {
            int V = this.numVertices;
            int[][] matrizAdj = new int[V][V];
            for (int u=0; u < V; u++)
                for (int v=0; v < V; v++)
                    matrizAdj[u][v] = 0;

            for (Aresta e : arestas)
                matrizAdj[e.u()][e.v()] = matrizAdj[e.v()][e.u()] = e.peso() + 1;

            StringBuilder build = new StringBuilder();
            for (int u=0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    build.append(matrizAdj[u][v]);
                    if (v < V - 1)
                        build.append(",");
                }
                build.append("\n");
            }

            return build.toString();
        }

        return "Grafo desconexo.";
    }

    public String getListaAdjacencia() {
        return Arrays.toString(Arrays.stream(listaAdjacencia).toArray());
    }
}
