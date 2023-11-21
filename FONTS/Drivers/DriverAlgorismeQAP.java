package Drivers;
import Domini.BranchandBound;
import Domini.Estrategia;

import java.util.*;
import java.io.*;

public class DriverAlgorismeQAP {
    public static void main(String[] args) {

        // el problema està escrit en forma de matriu

        Map<String, Integer> mapaPalabrasFrec = new HashMap<>();
        mapaPalabrasFrec.put("hola", 1);
        mapaPalabrasFrec.put("aloha", 2);
        mapaPalabrasFrec.put("ha", 3);

        Set<Character> conjuntoLetras = new HashSet<>();
        conjuntoLetras.add('h');
        conjuntoLetras.add('o');
        conjuntoLetras.add('l');
        conjuntoLetras.add('a');

        int nFilas = 2;
        int nColumnas = 2;

        Estrategia estrategia = new BranchandBound();
        char [][] matriz = estrategia.calculaDisposicio(mapaPalabrasFrec, conjuntoLetras, nFilas, nColumnas);

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz[0].length; ++j) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

    }
}