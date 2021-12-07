package Grafo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Grafo {
    private final int numVertices;
    private int numArestas;
    private int pesoTotal;
    private LinkedList<Aresta>[] AL;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.numArestas = 0;
        this.pesoTotal = 0;
        this.AL = new LinkedList[numVertices];
        for (int i = 0; i < this.AL.length; i++)
            AL[i] = new LinkedList<>();
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public @Nullable LinkedList<Aresta> getArestas() {
        if (this.numArestas > 0) {
            LinkedList<Aresta> arestas = new LinkedList<>();
            for (int i = 0; i < this.numVertices; i++)
                for (int j = 0; j < AL[i].size(); j++) {
                    Aresta aresta = AL[i].get(i);
                    if(!arestas.contains(aresta))
                        arestas.add(aresta);
                }
            return arestas;
        }
        return null;
    }

    public void adicionarAresta(int u, int v, int peso) {
        try {
            this.pesoTotal += peso;
            this.AL[u].add(new Aresta(u, v, peso));
            this.AL[v].add(new Aresta(v, u, peso));
            this.numArestas++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void adicionarAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();
            int peso = qual.getPeso();
            this.pesoTotal += peso;
            this.AL[u].add(new Aresta(u, v, peso));
            this.AL[v].add(new Aresta(v, u, peso));
            this.numArestas++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(int u, int v) {
        try {
            int peso = -1;
            for(Aresta aresta : this.AL[u])
                if(aresta.getV() == v) {
                    peso = aresta.getPeso();
                    break;
                }

            if (peso == -1)
                throw new NullPointerException("Aresta inexistente. Peso retornou -1.");

            this.pesoTotal -= peso;
            this.AL[u].removeIf(a -> a.getV() == v);
            this.AL[v].removeIf(a -> a.getU() == u);
            this.numArestas--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void removerAresta(@NotNull Aresta qual) {
        try {
            int u = qual.getU();
            int v = qual.getV();

            int peso = qual.getPeso();
            this.pesoTotal -= peso;
            this.AL[u].removeIf(a -> a.getV() == v);
            this.AL[v].removeIf(a -> a.getU() == u);
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
                        lista.add(listaAdj[i][j]);
            return lista;
        }
        return null;
    }

    public boolean arestaExiste(int u, int v) {
        return listaAdj[u][v] != -1;
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
                    print.append(listaAdj[i][j] + 1);
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
