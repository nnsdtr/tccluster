package Entrada;

import Grafo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LerArquivo
{
    //#region Atributos (Variaveis). (listaAlunos, listaAreaDePesquisa)
    public ArrayList<Aluno> listaAlunos =  new ArrayList<Aluno>();
    public ArrayList<AreaDePesquisa> listaAreaDePesquisa = new ArrayList<AreaDePesquisa>();
    public MatrizDissimilaridade mtrxDiss = new MatrizDissimilaridade();
    //#endregion

    //#region Metodos para a Leitura dos arquivos
    private Aluno criarAluno(int IDAluno, AreaDePesquisa areaDePesquisa)
    {
        Aluno novo = null;
        Aluno auxA = new Aluno(IDAluno, areaDePesquisa);
        novo = auxA;
        return novo;
    }

    private AreaDePesquisa criarAreaDePesquisa(String NomeAreaDePesquisa, int idPesquisa)
    {
        AreaDePesquisa novo = null;
        AreaDePesquisa auxADP = new AreaDePesquisa(NomeAreaDePesquisa, idPesquisa);
        novo = auxADP;
        return novo;
    }

    public void LerAluno() throws IOException // Carregou todos da lista.
    {
        // Método leitura de arquivo.
        FileReader fr = new FileReader("files/entrada10.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        while ((linha = br.readLine()) != null)
        {
            String[] auxL = linha.split(" ");

            Aluno novoA = criarAluno(Integer.parseInt(auxL[0]), listaAreaDePesquisa.get(Integer.parseInt(auxL[1])-1));
            listaAlunos.add(novoA);

        }
        fr.close();
        br.close();
    }

    public void LerAreaDePesquisa() throws IOException // Carregou todos da lista.
    {
        // Método leitura de arquivo.
        FileReader fr = new FileReader("files/areaPesquisaNome.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";

        int id = 1;
        while ((linha = br.readLine()) != null)
        {
            String[] auxL = linha.split("\n");

            AreaDePesquisa novoADP = criarAreaDePesquisa(auxL[0], id);
            listaAreaDePesquisa.add(novoADP);

            id++;
        }
        fr.close();
        br.close();
    }

    public void lerDissimilaridade() throws IOException {
        File arquivoPedidos = new File("files/matrizDissimilaridade.txt");
        BufferedReader br = new BufferedReader(new FileReader(arquivoPedidos));

        String [] valores;

        int l = 0;
        for (String linha; (linha = br.readLine()) != null; ){
            linha = linha.trim();
            valores = linha.split(" ? ");

            int p = 0;
            for (int c = 20 - valores.length; c < 20; c++){
                mtrxDiss.setMatriz(l,c,Integer.parseInt(valores[p]));
                p++;
            }

            l++;
        }
        br.close();
    }
    //#endregion

    public Grafo setGrafo(){
        int qtAlunos = this.listaAlunos.size();
        Grafo graf = new Grafo(qtAlunos);
        for (int i = 0; i < qtAlunos; i++){
            for (int j = i+1; j < qtAlunos;j++){
                int areaI = this.listaAlunos.get(i).getAreaDePesquisa().getIdPesquisa();
                int areaJ = this.listaAlunos.get(j).getAreaDePesquisa().getIdPesquisa();
                int peso = this.mtrxDiss.getDissimilaridade(areaI,areaJ);
                graf.adicionarAresta(i, j, peso);
            }
        }
        return graf;
    }
}
