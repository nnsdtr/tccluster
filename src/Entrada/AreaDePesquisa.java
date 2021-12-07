package Entrada;

public class AreaDePesquisa
{
    //#region Atributos (Variaveis). (NomeAreaDePesquisa)
    private String NomeAreaDePesquisa;
    private int idPesquisa;
    //#endregion

    //#region Construtores.
    public AreaDePesquisa(String NomeAreaDePesquisa, int idPesquisa)
    {
        Init(NomeAreaDePesquisa, idPesquisa);
    }

    // Inicializador de Objetos
    private void Init(String NomeAreaDePesquisa, int idPesquisa)
    {
        this.NomeAreaDePesquisa = NomeAreaDePesquisa;
        this.idPesquisa = idPesquisa;
    }
    //#endregion

    //#region Getters e Setters.
    public String getNomeAreaDePesquisa()
    {
        return this.NomeAreaDePesquisa;
    }

    public void setNomeAreaDePesquisa(String NomeAreaDePesquisa)
    {
        this.NomeAreaDePesquisa = NomeAreaDePesquisa;
    }

    public int getIdPesquisa() {
        return idPesquisa;
    }

    public void setIdPesquisa(int idPesquisa) {
        this.idPesquisa = idPesquisa;
    }

    //#endregion

    //#region Metodos
    @Override
    public String toString()
    {
        StringBuilder resposta = new StringBuilder();
        resposta.append("\nNomeAreaDePesquisa: " + this.NomeAreaDePesquisa + "\nidPesquisa: " + this.idPesquisa);
        return resposta.toString();
    }
    //#endregion
}
