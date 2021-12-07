package Grafo;

import Entrada.AreaDePesquisa;
import org.jetbrains.annotations.NotNull;

public class Vertice implements Comparable<Vertice> {
    private final int id;
    private Cor cor;

    public Vertice(int u) {
        this.id = u;
        this.pai = null;
        this.cor = Cor.BRANCO;
        this.componente = -1;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getID() {
        return this.id;
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public int compareTo(Vertice outro) {
        return this.id - outro.id;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        Vertice outro = (Vertice) obj;

        return this.id == outro.id;
    }

    @Override
    public String toString(){
        return "Aluno " + (this.getID()+1) + " (" + this.areaDePesquisa + ')';
    }
}
