package Grafo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Grafo {
    private final int numVertices;
    private final LinkedList<Aresta>[] AL;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.AL = new LinkedList[numVertices];
        for (int v = 0; v < numVertices; v++)
            AL[v] = new LinkedList<>();
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public void adicionarAresta(int u, int v, int peso) {
        try {
            this.AL[u].add(new Aresta(u, v, peso));
            this.AL[v].add(new Aresta(v, u, peso));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void adicionarAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();
            this.AL[u].add(new Aresta(u, v, qual.getPeso()));
            this.AL[v].add(new Aresta(v, u, qual.getPeso()));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(int u, int v) {
        try {
            this.AL[u].removeIf(a -> a.getV() == v);
            this.AL[v].removeIf(a -> a.getU() == u);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();
            this.AL[u].removeIf(a -> a.getV() == v);
            this.AL[v].removeIf(a -> a.getU() == u);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public @Nullable LinkedList<Aresta> getArestas() {
        LinkedList<Aresta> arestas = new LinkedList<>();
        for (int u = 0; u < this.numVertices; u++)
            for (int v = 0; v < AL[u].size(); v++) {
                Aresta e = AL[u].get(v);
                if(!arestas.contains(e))
                    arestas.add(e);
            }

        if (arestas.size() > 1)
            return arestas;
        return null;
    }

    public String toString() {
        LinkedList<Aresta> arestas = this.getArestas();
        if (arestas != null) {
            StringBuilder build = new StringBuilder();
            for (Aresta e : arestas)
                build.append(e.getU()).append("-").append(e.getV()).append(" ");
            return build.toString();
        }

        return "Grafo desconexo.";
    }

    public String outputMatrizAdj() {
        LinkedList<Aresta> arestas = this.getArestas();
        if (arestas != null) {
            int V = this.numVertices;
            int[][] matrizAdj = new int[V][V];
            for (int u=0; u < V; u++)
                for (int v=0; v < V; v++)
                    matrizAdj[u][v] = 0;

            for (Aresta e : arestas)
                matrizAdj[e.getU()][e.getV()] = matrizAdj[e.getV()][e.getU()] = e.getPeso() + 1;

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

    public String getAL() {
        return Arrays.toString(Arrays.stream(AL).toArray());
    }
}
