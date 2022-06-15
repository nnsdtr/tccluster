package Grafo;

import Entrada.*;
import org.jetbrains.annotations.NotNull;

public class Vertice implements Comparable<Vertice> {
    private final Aluno aluno;
    private Cor cor;

    public Vertice(Aluno aluno) {
        this.aluno = aluno;
        this.cor = Cor.BRANCO;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getID() {
        return this.aluno.getID();
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public int compareTo(Vertice outro) {
        return this.getID() - outro.getID();
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (this.getClass() != obj.getClass())
            return false;

        Vertice outro = (Vertice) obj;
        return this.aluno == outro.aluno;
    }

    @Override
    public String toString(){
        return "Aluno " + (this.getID()+1) + " (" + this.aluno.getAreaDePesquisa().getNomeAreaDePesquisa() + ")";
    }
}
