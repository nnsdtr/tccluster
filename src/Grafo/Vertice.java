package Grafo;

public class Vertice implements Comparable<Vertice> {
    private final int id;
    private Vertice pai;
    private Cor cor;
    private int componente;
    private int descoberta;
    private int termino;

    public Vertice(int u) {
        this.id = u;
        this.pai = null;
        this.cor = Cor.BRANCO;
        this.componente = -1;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public void setComponente(int componente) {
        this.componente = componente;
    }

    public void setDescoberta(int descoberta) {
        this.descoberta = descoberta;
    }

    public void setTermino(int termino) {
        this.termino = termino;
    }

    public int getID() {
        return this.id;
    }

    public Vertice getPai() {
        return this.pai;
    }

    public Cor getCor() {
        return cor;
    }

    public int getComponente() {
        return componente;
    }

    public int getDescoberta() {
        return descoberta;
    }

    public int getTermino() {
        return termino;
    }

    @Override
    public int compareTo(Vertice outro) {
        return this.id - outro.id;
    }

    @Override
    public String toString(){
        return "Aluno " + (this.getID()+1);
    }
}
