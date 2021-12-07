import Algoritmos.*;
import Entrada.LerArquivo;
import Grafo.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int NUM_PROFESSORES = 2;

        LerArquivo leitor = new LerArquivo();
        leitor.lerAreaDePesquisa();
        leitor.lerDissimilaridade();
        leitor.lerAluno();
        Grafo kn = leitor.setGrafo();

        Kruskal krus = new Kruskal(kn);
        Grafo agm = krus.buildAGM();

        Cluster cluster = new Cluster(agm);
        cluster.build(NUM_PROFESSORES);

        System.out.println(cluster);
    }
}
