package JUnit;

import Domini.*;
import Domini.LlistaFrequencies;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;
import Stubs.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class HungarianAlgorithmTest {

    @Test
    public void pas1_ReduceMatriz_DeberiaReducirMatrizCorrectamente() {
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
    public void pas2_MarksZerosCorrectly_ShouldMarkZerosInMatrix() {
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
}
