package Entrada;

public class Aluno
{
    private int IDAluno;
    private AreaDePesquisa areaDePesquisa;

    public Aluno(int IDAluno, AreaDePesquisa areaDePesquisa)
    {
        init(IDAluno, areaDePesquisa);
    }

    private void init(int IDAluno, AreaDePesquisa areaDePesquisa)
    {
        this.IDAluno = IDAluno - 1;
        this.areaDePesquisa = areaDePesquisa;
    }

    public int getID()
    {
        return this.IDAluno;
    }

    public void setIDAluno(int IDAluno)
    {
        this.IDAluno = IDAluno;
    }


    public AreaDePesquisa getAreaDePesquisa() {
        return areaDePesquisa;
    }

    public void setAreaDePesquisa(AreaDePesquisa areaDePesquisa) {
        this.areaDePesquisa = areaDePesquisa;
    }

    @Override
    public String toString()
    {
        StringBuilder resposta = new StringBuilder();
        resposta.append("\nIDAluno: " + this.IDAluno + " - IDAreaDePesquisa: " + this.areaDePesquisa.getIdPesquisa());
        return resposta.toString();
    }
}

