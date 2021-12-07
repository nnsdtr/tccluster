import Algoritmos.Cluster;
import Grafo.*;
import Algoritmos.Kruskal;

public class App {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        int numVertices = 1000;
        Grafo teste = new Grafo(numVertices);

        for (int i = 0; i < numVertices - 1; i++)
            for (int j = i + 1; j < numVertices; j++)
                teste.adicionarAresta(i, j, getRandomNumber(0, 100));

        System.out.println(teste.outputMatrizAdj());


        Kruskal kruskal = new Kruskal(teste);
        Grafo agm = kruskal.build();

        System.out.println(agm.outputMatrizAdj());

        Cluster clu = new Cluster(agm);
        Grafo resultado = clu.clusterizar(5);

        System.out.println(resultado.outputMatrizAdj());

    }
}
