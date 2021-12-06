package Grafo;

public class Aresta implements Comparable<Aresta> {
    private final int u, v, peso;

    public Aresta(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }

    public int getU() {
        return this.u;
    }

    public int getV() {
        return this.v;
    }

    public int getPeso() {
        return this.peso;
    }

    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }

    public boolean equals(int u, int v) {
        return (this.u == u & this.v == v) ||
                (this.u == v & this.v == u);
    }

    public String toString() {
        if(this.u < this.v)
            return this.u + "-" + this.v + " (peso: " + this.peso + ")";
        return this.v + "-" + this.u + " (peso: " + this.peso + ")";
    }
}
