package Drivers;
import Domini.HungarianAlgorithm;

import java.util.*;
import java.io.*;

public class DriverHungarianAlgorithm {
    public static void main(String[] args) {

        // el problema està escrit en forma de matriu
        List<List<List<Integer>>> tests = new ArrayList<>();

        // Afegir una matriu tridimensional a la llista
        tests.add(List.of(
                List.of(25, 40, 35),
                List.of(40, 60, 35),
                List.of(20, 40, 25)
        ));

        tests.add(List.of(
                List.of(64, 18, 75),
                List.of(97, 60, 24),
                List.of(87, 63, 15)
        ));

        tests.add(List.of(
                List.of(85, 51, 67, 10),
                List.of(29, 81, 86, 68),
                List.of(64, 88, 99, 89),
                List.of(93, 75, 22, 94)
        ));
        tests.add(List.of(
                List.of(77, 81, 27, 88),
                List.of(16, 39, 26, 38),
                List.of(41, 87, 20, 60),
                List.of(77, 11, 41, 72)
        ));

        tests.add(List.of(
                List.of(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36),
                List.of(36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35),
                List.of(35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34),
                List.of(34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33),
                List.of(33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32),
                List.of(32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31),
                List.of(31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
                List.of(30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29),
                List.of(29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28),
                List.of(28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27),
                List.of(27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26),
                List.of(26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25),
                List.of(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24),
                List.of(24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23),
                List.of(23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22),
                List.of(22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21),
                List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                List.of(20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19),
                List.of(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17, 18),
                List.of(18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16, 17),
                List.of(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15, 16),
                List.of(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14, 15),
                List.of(15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13, 14),
                List.of(14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12, 13),
                List.of(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11, 12),
                List.of(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10, 11),
                List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 10)

                ));

        int testNumber = 1;
        // trobar l'assignació òptima
        for (List<List<Integer>> m : tests) {
            System.out.println("\n");
            System.out.print("PROVA " + testNumber +"\n");
            System.out.println("----------------- \n");
            int[][] matriu = convertirLlistaAMatriuBidimensional(m);
            HungarianAlgorithm ha = new HungarianAlgorithm(matriu);
            int[][] assignacio = ha.trobarAssignacioOptima();

            if (assignacio.length > 0) {
                // imprimir l'assignació
                for (int i = 0; i < assignacio.length; i++) {
                    System.out.print("Posició (columna): " + assignacio[i][0] + " => Lletra (fila): " + assignacio[i][1]);
                    System.out.print("\n");
                }
            } else {
                System.out.println("Cap assignació trobada!");
            }
            ++testNumber;
        }
    }

    private static int[][] convertirLlistaAMatriuBidimensional(List<List<Integer>> llista) {
        int[][] array = new int[llista.size()][];
        for (int i = 0; i < llista.size(); i++) {
            List<Integer> fila = llista.get(i);
            array[i] = new int[fila.size()];
            for (int j = 0; j < fila.size(); j++) {
                array[i][j] = fila.get(j);
            }
        }
        return array;
    }
}