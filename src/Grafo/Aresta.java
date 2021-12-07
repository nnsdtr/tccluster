package Grafo;

import org.jetbrains.annotations.NotNull;

public record Aresta(Vertice u, Vertice v, int peso) implements Comparable<Aresta> {

    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        Aresta comparado = (Aresta) obj;
        return (this.u.getID() == comparado.u.getID() & this.v.getID() == comparado.v.getID()) ||
                (this.u.getID() == comparado.v.getID() & this.v.getID() == comparado.u.getID());
    }

    public String toString() {
        int U = this.u.getID() + 1;
        int V = this.v.getID() + 1;

        if (U < V)
            return U + "-" + V + " (peso: " + this.peso + ")";
        return V + "-" + U + " (peso: " + this.peso + ")";
    }

    public Vertice getAdjacente(int id) {
        if (this.u.getID() == id)
            return this.v;
        else if (this.v.getID() == id)
            return this.u;
        else
            throw new IllegalArgumentException("id incorreto para o Vértice.");
    }

    public Vertice getVertice(int id) {
        if (this.u.getID() == id)
            return this.u;
        else if (this.v.getID() == id)
            return this.v;
        else
            throw new IllegalArgumentException("id incorreto para o Vértice.");
    }

    public String[] dataOutput() {
        return new String[] {
            this.u.toString(),
            this.v.toString()
        };
    }
}
