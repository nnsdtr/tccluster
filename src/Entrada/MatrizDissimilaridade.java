package Entrada;

import java.util.Arrays;

public class MatrizDissimilaridade {
    private final int [][] matriz;

    public MatrizDissimilaridade() {
        this.matriz = new int[20][20];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void adicionarValor(int l, int c, int val) {
        this.matriz[l][c] = val;
    }

    public int getDissimilaridade (int l, int c){
        if (l<=c){
            return this.matriz[l-1][c-1];
        }else {
            return this.matriz[c-1][l-1];
        }
    }

    @Override
    public String toString() {
        String aux = "";
        for (int[] ints : this.matriz) {
            aux = aux.concat("\n").concat((Arrays.toString(ints)));
        }
        return aux;
    }
}
