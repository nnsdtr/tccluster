package Entrada;

public class Aluno
{
    //#region Atributos (Variaveis). (IDAluno, IDAreaDePesquisa)
    private int IDAluno;
    private AreaDePesquisa areaDePesquisa;
    //private String NomeAreaDePesquisa;
    //#endregion

    //#region Construtores.
    public Aluno(int IDAluno, AreaDePesquisa areaDePesquisa)
    {
        Init(IDAluno, areaDePesquisa);
    }

    // Inicializador de Objetos
    private void Init(int IDAluno, AreaDePesquisa areaDePesquisa)
    {
        this.IDAluno = IDAluno;
        this.areaDePesquisa = areaDePesquisa;
    }
    //#endregion

    //#region Getters e Setters.
    public int getIDAluno()
    {
        return this.IDAluno;
    }

    public void setIDAluno(int IDAluno)
    {
        this.IDAluno = IDAluno;
    }
    //***************************************************


    public AreaDePesquisa getAreaDePesquisa() {
        return areaDePesquisa;
    }

    public void setAreaDePesquisa(AreaDePesquisa areaDePesquisa) {
        this.areaDePesquisa = areaDePesquisa;
    }
    //#endregion

    //#region Metodos
    @Override
    public String toString()
    {
        StringBuilder resposta = new StringBuilder();
        resposta.append("\nIDAluno: " + this.IDAluno + " - IDAreaDePesquisa: " + this.areaDePesquisa.getIdPesquisa());
        return resposta.toString();
    }
    //#endregion
}

