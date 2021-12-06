package Grafo;

import java.util.NoSuchElementException;

public class Aresta implements Comparable<Aresta> {
    private final int v1;
    private final int v2;
    private final int peso;

    public Aresta(int u, int v, int peso) {
        this.v1 = u;
        this.v2 = v;
        this.peso = peso;
    }

    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }

    public boolean equals(int u, int v) {
        return (this.v1 == u & this.v2 == v) ||
                (this.v1 == v & this.v2 == u);
    }
}
