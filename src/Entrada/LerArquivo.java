package Entrada;

import Grafo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LerArquivo
{
    private final ArrayList<Aluno> listaAlunos =  new ArrayList<>();
    private final ArrayList<AreaDePesquisa> listaAreaDePesquisa = new ArrayList<>();
    private final MatrizDissimilaridade matrizDissimilaridade = new MatrizDissimilaridade();

    public void lerAluno(String filepath) throws IOException // Carregou todos da lista.
    {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        String linha;

        while ((linha = br.readLine()) != null)
        {
            String[] auxL = linha.split(" ");
            Aluno novoA = new Aluno(Integer.parseInt(auxL[0]), listaAreaDePesquisa.get(Integer.parseInt(auxL[1])-1));
            listaAlunos.add(novoA);
        }
        fr.close();
        br.close();
    }

    public void lerAreaDePesquisa() throws IOException // Carregou todos da lista.
    {
        FileReader fr = new FileReader("files/areaPesquisaNome.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha;

        int id = 0;
        while ((linha = br.readLine()) != null)
        {
            String[] auxL = linha.split("\n");

            AreaDePesquisa novoADP = new AreaDePesquisa(auxL[0], id);
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
                matrizDissimilaridade.adicionarValor(l,c,Integer.parseInt(valores[p]));
                p++;
            }

            l++;
        }
        br.close();
    }

    public MatrizDissimilaridade getMatrizDissimilaridade() {
        return this.matrizDissimilaridade;
    }

    public Grafo setGrafo(){
        int qtAlunos = this.listaAlunos.size();
        Grafo grafo = new Grafo(qtAlunos);
        for (int i = 0; i < qtAlunos; i++) {
            Aluno alunoI = this.listaAlunos.get(i);
            for (int j = i+1; j < qtAlunos;j++) {
                Aluno alunoJ = this.listaAlunos.get(j);
                int peso = this.matrizDissimilaridade.getDissimilaridade(
                    alunoI.getAreaDePesquisa().getIdPesquisa(),
                    alunoJ.getAreaDePesquisa().getIdPesquisa()
                );
                grafo.adicionarAresta(alunoI, alunoJ, peso);
            }
        }
        return grafo;
    }
}
