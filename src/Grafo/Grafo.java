package Grafo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Grafo {
    private final int numVertices;
    private final ArrayList<Aresta> arestas;
    private final ArrayList<Vertice> vertices;
    private final ArrayList<Aresta>[] listaAdjacencia;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.listaAdjacencia = new ArrayList[numVertices];
        for (int v = 0; v < numVertices; v++)
            listaAdjacencia[v] = new ArrayList<>();
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public void adicionarAresta(@NotNull Aresta qual) {
        try {
            this.listaAdjacencia[qual.u().getID()].add(qual);
            this.listaAdjacencia[qual.v().getID()].add(qual);

            if (!this.arestas.contains(qual))
                this.arestas.add(qual);

            if (!this.vertices.contains(qual.u()))
                this.vertices.add(qual.u());

            if(!this.vertices.contains(qual.v()))
                this.vertices.add(qual.v());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public void adicionarAresta(int u, int v, int peso) {
        try {
            Vertice U = buscarVertice(u);
            Vertice V = buscarVertice(v);
            if(U == null) {
                U = new Vertice(u);
                this.vertices.add(U);
            }
            if(V == null) {
                V = new Vertice(v);
                this.vertices.add(V);
            }

            Aresta nova = new Aresta(U, V, peso);
            this.listaAdjacencia[u].add(nova);
            this.listaAdjacencia[v].add(nova);
            this.arestas.add(nova);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public @Nullable Vertice buscarVertice(int u) {
        for (Vertice vertice : this.vertices)
            if(vertice.getID() == u)
                return vertice;
        return null;
    }

    public void removerAresta(@NotNull Aresta qual) {
        try {
            this.listaAdjacencia[qual.u().getID()].remove(qual);
            this.listaAdjacencia[qual.v().getID()].remove(qual);
            this.arestas.remove(qual);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida para matriz de adjacência.");
        }
    }

    public @Nullable ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    public @Nullable ArrayList<Vertice> getAdjacentes(Vertice u) {
        ArrayList<Aresta> arestasDeU = this.listaAdjacencia[u.getID()];
        ArrayList<Vertice> adjacentes = new ArrayList<>();
        for (Aresta aresta : arestasDeU) {
            Vertice adjacente = aresta.getAdjacente(u.getID());
            adjacentes.add(adjacente);
        }
        return adjacentes;
    }

    public String toString() {
        StringBuilder build = new StringBuilder();
        for (Aresta e : arestas)
            build.append(e.u().getID()).append("-").append(e.v().getID()).append(" ");
        return build.toString();
    }

    public String outputMatrizAdj() {
        int numeroVertices = this.numVertices;
        int[][] matrizAdj = new int[numeroVertices][numeroVertices];
        for (int u=0; u < numeroVertices; u++)
            for (int v=0; v < numeroVertices; v++)
                matrizAdj[u][v] = 0;

        for (Aresta e : arestas) {
            matrizAdj[e.u().getID()][e.v().getID()] = e.peso() + 1;
            matrizAdj[e.v().getID()][e.u().getID()] = e.peso() + 1;
        }

        StringBuilder build = new StringBuilder();
        for (int u=0; u < numeroVertices; u++) {
            for (int v = 0; v < numeroVertices; v++) {
                build.append(matrizAdj[u][v]);
                if (v < numeroVertices - 1)
                    build.append(",");
            }
            build.append("\n");
        }

        return build.toString();

    }



    public void outputCSV(String CSV_FILE_NAME) throws FileNotFoundException {
        List<String[]> dataLines = new ArrayList<>();
        for (Aresta e : this.arestas)
            dataLines.add(e.dataOutput());

        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("Source,Target");
            dataLines.stream()
                .map(this::convertToCSV)
                .forEach(pw::println);
        }
    }

    private String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    public ArrayList<Aresta>[] getListaAdjacencia() {
        return listaAdjacencia;
    }

    public String listaAdjacenciaString() {
        return Arrays.toString(Arrays.stream(listaAdjacencia).toArray());
    }
}
