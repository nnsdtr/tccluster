import Grafo.Grafo;
import KruskalAGM.AGM;

public class App {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        int numVertices = 10_000;
        Grafo teste = new Grafo(numVertices);

        long startTime = System.nanoTime();
        for (int i = 0; i < numVertices - 1; i++)
            for (int j = i + 1; j < numVertices; j++)
                teste.adicionarAresta(i, j, getRandomNumber(0, 100));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Tempo Build: " + duration/1000000 + " ns");
//        System.out.println(teste.outputMatrizAdj());
//        System.out.println();

        startTime = System.nanoTime();

        AGM agm = new AGM(teste);
        Grafo grafo = agm.build();

//        System.out.println(grafo.outputMatrizAdj());

        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Tempo AGM: " + duration/1000000  + " ns");
    }
}
