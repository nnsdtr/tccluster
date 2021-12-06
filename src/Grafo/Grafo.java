package Grafo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo {
    private final int numVertices;
    private int numArestas;
    private int pesoTotal;
    private final int[][] matrizAdjacencia;     // Matriz para grafos Kn

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.numArestas = 0;
        this.pesoTotal = 0;
        this.matrizAdjacencia = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
                this.matrizAdjacencia[i][j] = -1;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public int[][] getMatrizAdjacencia() {
        return this.matrizAdjacencia;
    }

    public @Nullable LinkedList<Aresta> getArestas() {
        if (this.numArestas > 0) {
            LinkedList<Aresta> arestas = new LinkedList<>();
            for (int i = 0; i < this.numVertices - 1; i++)
                for (int j = i + 1; j < this.numVertices; j++)
                    if(arestaExiste(i, j))
                        arestas.add(new Aresta(i, j, matrizAdjacencia[i][j]));

            return arestas;
        }
        return null;
    }

    public void adicionarAresta(int u, int v, int peso) {
        try {
            this.pesoTotal += peso;
            this.matrizAdjacencia[u][v] = peso;
            this.matrizAdjacencia[v][u] = peso;
            this.numArestas++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void adicionarAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();
            this.pesoTotal += qual.getPeso();
            this.matrizAdjacencia[u][v] = qual.getPeso();
            this.matrizAdjacencia[v][u] = qual.getPeso();
            this.numArestas++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(int u, int v) {
        try {
            this.pesoTotal -= this.matrizAdjacencia[u][v];
            this.matrizAdjacencia[u][v] = -1;
            this.matrizAdjacencia[v][u] = -1;
            this.numArestas--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();
            this.pesoTotal -= qual.getPeso();
            this.matrizAdjacencia[u][v] = -1;
            this.matrizAdjacencia[v][u] = -1;
            this.numArestas--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public double desvioPadraoPesos() {
        if (this.numArestas > 1) {
            double mediaPesos = this.mediaPesos();
            ArrayList<Integer> pesos = this.getPesos();

            double soma = 0d;
            for (Integer peso : pesos)
                soma += Math.pow(peso - mediaPesos, 2);

            return Math.sqrt(soma/this.numArestas);
        }
        return 0;
    }

    public double mediaPesos() {
        if (this.numArestas > 0)
            return this.pesoTotal / (double) this.numArestas;
        else
            return 0;
    }

    private @Nullable ArrayList<Integer> getPesos() {
        if (this.numArestas > 0) {
            ArrayList<Integer> lista = new ArrayList<>();
            for (int i = 0; i < this.numVertices - 1; i++)
                for (int j = i + 1; j < this.numVertices; j++)
                    if(arestaExiste(i, j))
                        lista.add(matrizAdjacencia[i][j]);
            return lista;
        }
        return null;
    }

    public boolean arestaExiste(int u, int v) {
        return matrizAdjacencia[u][v] != -1;
    }

    public String toString() {
        if (this.numArestas > 0) {
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < this.numVertices - 1; i++)
                for (int j = i + 1; j < this.numVertices; j++)
                    if(arestaExiste(i, j))
                        print.append(i+1).append("-").append(j+1).append(" ");

            return print.toString();
        }

        return "Grafo desconexo.";
    }

    public String outputMatrizAdj() {
        if (this.numArestas > 0) {
            StringBuilder print = new StringBuilder();

            for (int i = 0; i < this.numVertices; i++) {
                for (int j = 0; j < this.numVertices; j++) {
                    print.append(matrizAdjacencia[i][j] + 1);
                    if (j < this.numVertices - 1)
                        print.append(",");
                }
                print.append("\n");
            }
            return print.toString();
        }

        return "Grafo desconexo.";
    }
}
