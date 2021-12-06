package Grafo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo {
    private final int numVertices;
    private int numArestas;
    private int pesoTotal;
    private final int[][] matrizAdjacencia;     // Matriz do tipo Kn

    public Grafo(int numVertices) {
        this.numArestas = 0;
        this.numVertices = numVertices;
        this.pesoTotal = 0;
        this.matrizAdjacencia = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
                this.matrizAdjacencia[i][j] = -1;
    }

    public int[][] getMatrizAdjacencia() {
        return this.matrizAdjacencia;
    }

    public LinkedList<Aresta> getArestas() {
        if (this.numArestas > 0) {
            LinkedList<Aresta> arestas = new LinkedList<Aresta>();
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

    public ArrayList<Integer> getPesos() {
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
                        print.append(i).append("-").append(j).append(" ");

            return print.toString();
        }

        return "Grafo desconexo.";
    }
}
