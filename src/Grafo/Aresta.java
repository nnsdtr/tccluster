package Grafo;

import org.jetbrains.annotations.NotNull;

public record Aresta(int u, int v, int peso) implements Comparable<Aresta> {

    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        Aresta comparado = (Aresta) obj;
        return (this.u == comparado.u & this.v == comparado.v) ||
                (this.u == comparado.v & this.v == comparado.u);
    }

    public String toString() {
        if (this.u < this.v)
            return this.u + "-" + this.v + " (peso: " + this.peso + ")";
        return this.v + "-" + this.u + " (peso: " + this.peso + ")";
    }
}
