import Algoritmos.*;
import Entrada.LerArquivo;
import Grafo.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int NUM_PROFESSORES = 5;

        LerArquivo leitor = new LerArquivo();
        leitor.lerAreaDePesquisa();
        leitor.lerDissimilaridade();
        leitor.lerAluno("files/entrada10.txt");
//        leitor.lerAluno("files/entrada30.txt");
//        leitor.lerAluno("files/entrada50.txt");
//        leitor.lerAluno("files/entrada100.txt");
//        leitor.lerAluno("files/entrada1000.txt");
//        leitor.lerAluno("files/entrada100000.txt");
        Grafo kn = leitor.setGrafo();

        Kruskal krus = new Kruskal(kn);
        Grafo agm = krus.buildAGM();

        Cluster cluster = new Cluster(agm);
        cluster.build(NUM_PROFESSORES);

        System.out.println(cluster);
    }
}
