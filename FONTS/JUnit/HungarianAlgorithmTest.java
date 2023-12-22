package JUnit;

import Domini.*;
import Domini.LlistaFrequencies;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class HungarianAlgorithmTest {

    @Test
    public void pas1() {
        double[][] resultat = {
                {7.3, 5.2, 3.1, 3.0},
                {3.4, 3.8, 5.7, 5.6},
                {7.9, 5.1, 4.0, 4.3},
                {4.2, 6.7, 5.5, 6.1}
        };
        HungarianAlgorithm ha = new HungarianAlgorithm(resultat);
        ha.pas1();
        double[][] expected = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], resultat[i], 0.01);
        }
    }
    @Test
    public void pas2() {
        double[][] matrix = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(matrix);
        ha.pas2();

        int[] expectedZeroFila = {3,0,2,-1};
        int[] expectedZeroColumna = {1,-1,2,0};

        assertArrayEquals(expectedZeroFila, ha.zeroFila);
        assertArrayEquals(expectedZeroColumna, ha.zeroColumna);
    }

    @Test
    public void pas3() {

        double[][] matrix = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(matrix);
        ha.pas2();
        ha.pas3();

        int[] expectedcolumnaCoberta = {1,0,1,1};

        assertArrayEquals(expectedcolumnaCoberta, ha.columnaCoberta);
    }

    @Test
    public void pas4() {
        double[][] matriu = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(matriu);
        ha.pas1();
        ha.pas2();
        ha.pas3();
        int [] zeroPrincipal = ha.pas4();
//        for (int i=0; i<zeroPrincipal.length; ++i) System.out.print(" " + zeroPrincipal[i] +" ");
//        assertTrue(true);

        int [] expectedzeroPrincipal = {1,1};
        assertArrayEquals(expectedzeroPrincipal, zeroPrincipal);
    }

    @Test
    public void pas5() {
        double[][] matriu = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(matriu);
        ha.pas1();
        ha.pas2();
        ha.pas3();

        int[] zeroPrincipal = ha.pas4();
        //zeroPrincipal = {1,1}

        ha.pas5(zeroPrincipal);

        assertEquals(1, ha.zeroFila[1]);  // Verifica que la fila del zero no estigui marcada
        assertEquals(0, ha.columnaCoberta[1]);  // Verifica que la columna del zero no estigui coberta
        assertEquals(-1, ha.zerosEstrellaEnFila[1]);  // Verifica que zerosEstrellaEnFila s'actualitzi correctament
    }


    @Test
    public void pas6() {
        double[][] matriu = {
                {4.3, 1.8, 0.1, 0.0},
                {0.0, 0.0, 2.3, 2.2},
                {3.9, 0.7, 0.0, 0.3},
                {0.0, 2.1, 1.3, 1.9},
        };

        HungarianAlgorithm ha = new HungarianAlgorithm(matriu);
        ha.pas1();
        ha.pas2();
        ha.pas3();
        int[] zeroPrincipal = ha.pas4();
        //zeroPrincipal = {1,1}
        ha.pas5(zeroPrincipal);
        double[][] copiaOriginal = ha.copiamatriu;  // Copia la matriz original

        // Realiza el paso 6
        ha.pas6();

        // Verifica que es resti el valor mínim no cobert als elementos correctes de la matriu
        // Verifica que les modificacions en la matriu siguin less esperades després d'executar el pas 6
        for (int i = 0; i < ha.matriu.length; i++) {
            for (int j = 0; j < ha.matriu[i].length; j++) {
                if (ha.filaCoberta[i] == 0 && ha.columnaCoberta[j] == 0) {
                    // Verifica que el valor no cubierto se haya reducido en la matriz
                    assertEquals(copiaOriginal[i][j] - ha.matriu[i][j], 0.0, 0.001);
                } else if (ha.filaCoberta[i] == 1 && ha.columnaCoberta[j] == 1) {
                    // Verifica que el valor cubierto se haya aumentado en la matriz
                    assertEquals(copiaOriginal[i][j] + ha.matriu[i][j], 0.0, 0.001);
                }
            }
        }
    }
}

//Classe Programada per: Agustí Costabella
