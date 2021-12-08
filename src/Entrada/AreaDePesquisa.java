package Entrada;

public class AreaDePesquisa
{
    private String NomeAreaDePesquisa;
    private int idPesquisa;

    public AreaDePesquisa(String NomeAreaDePesquisa, int idPesquisa)
    {
        init(NomeAreaDePesquisa, idPesquisa);
    }

    private void init(String NomeAreaDePesquisa, int idPesquisa)
    {
        this.NomeAreaDePesquisa = NomeAreaDePesquisa;
        this.idPesquisa = idPesquisa;
    }

    public String getNomeAreaDePesquisa()
    {
        return this.NomeAreaDePesquisa;
    }

    public int getIdPesquisa() {
        return idPesquisa;
    }

    @Override
    public String toString()
    {
        StringBuilder resposta = new StringBuilder();
        resposta.append("\nNomeAreaDePesquisa: " + this.NomeAreaDePesquisa + "\nidPesquisa: " + this.idPesquisa);
        return resposta.toString();
    }
}
