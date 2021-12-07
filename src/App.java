import Algoritmos.Cluster;
import Grafo.*;
import Algoritmos.Kruskal;

public class App {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        int numVertices = 10_000;
        long start;
        Grafo teste = new Grafo(numVertices);

        start = System.currentTimeMillis();
        for (int i = 0; i < numVertices - 1; i++)
            for (int j = i + 1; j < numVertices; j++)
                teste.adicionarAresta(i, j, getRandomNumber(0, 100));

        System.out.println("Kn Graph Build: " + (System.currentTimeMillis() - start)/1000 + "s");
//        System.out.println(teste.outputMatrizAdj());

        start = System.currentTimeMillis();
        Kruskal kruskal = new Kruskal(teste);
        Grafo agm = kruskal.buildAGM();

        System.out.println("Kruskal Algorithm: " + (System.currentTimeMillis() - start)/1000 + "s");
//        System.out.println(agm.outputMatrizAdj());

        start = System.currentTimeMillis();
        Cluster clu = new Cluster(agm);
        Grafo resultado = clu.clusterizar(5);

        System.out.println("Clusterize: " + (System.currentTimeMillis() - start)/1000 + "s");
//        System.out.println(resultado.outputMatrizAdj());

    }
}
