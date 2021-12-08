import Algoritmos.*;
import Entrada.LerArquivo;
import Grafo.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int NUM_PROFESSORES = 4;

        LerArquivo leitor = new LerArquivo();
        leitor.lerAreaDePesquisa();
        leitor.lerDissimilaridade();
        leitor.lerAluno("files/entrada100.txt");
        Grafo kn = leitor.setGrafo();

//        System.out.println(kn.outputMatrizAdj());

        Cluster cluster = new Cluster(kn);
        cluster.build(NUM_PROFESSORES);


//        System.out.println(cluster.getAgm().outputMatrizAdj());

        System.out.println(cluster);
    }
}
