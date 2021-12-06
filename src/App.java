import Grafo.Grafo;
import KruskalAGM.KruskalAGM;

public class App {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        Grafo teste = new Grafo(5);
        int numVertices = 5;

        for (int i = 0; i < numVertices - 1; i++)
            for (int j = i + 1; j < numVertices; j++)
                teste.adicionarAresta(i, j, getRandomNumber(0, 100));


        System.out.println(teste.outputMatrizAdj());
        System.out.println();

        KruskalAGM krus = new KruskalAGM(teste);
        Grafo agm = krus.buildAGM();

        System.out.println(agm.outputMatrizAdj());
    }
}
