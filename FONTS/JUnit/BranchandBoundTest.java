package JUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import Domini.*;


import java.util.*;


public class BranchandBoundTest {
    Map<String, Integer> mapaPalabrasFrec = new HashMap<>();
    Set<Character> conjuntoLetras = new HashSet<>();

    int n_filas, n_columnas;

    @Before
    public void omplirFrecuencias() {
        mapaPalabrasFrec.put("hola", 1);
        mapaPalabrasFrec.put("aloha", 2);
        mapaPalabrasFrec.put("ha", 3);
    }

    @Before
    public void omplirLetras() {
        conjuntoLetras.add('h');
        conjuntoLetras.add('o');
        conjuntoLetras.add('l');
        conjuntoLetras.add('a');
    }

    @Before
    public void omplirFilas_Columnas() {
        n_filas = 2;
        n_columnas = 2;
    }

    @Test
    public void calculoMatriz_distancias() {
        BranchandBound b = new BranchandBound();
        int n = conjuntoLetras.size();
        double[][] matriz_dist = new double[n][n];
        matriz_dist = b.calculaMatDist( n, n_filas, n_columnas);
        double[][] matrizEsperada = {
                {0.0 ,1.0, 1.0, 1.4142135623730951},
                {1.0, 0.0, 1.4142135623730951, 1.0},
                {1.0, 1.4142135623730951, 0.0, 1.0},
                {1.4142135623730951, 1.0, 1.0, 0.0}
        };
        assertArrayEquals(matrizEsperada, matriz_dist);

    }

    @Test
    public void calculoMatriz_frecuencias() {
        BranchandBound b = new BranchandBound();
        int n = conjuntoLetras.size();
        double[][] matriz_frec = new double[n][n];
        matriz_frec = b.calculaMatDist( n, n_filas, n_columnas);
        double[][] matrizEsperada = {
                {0.0, 0.0, 2.0, 0.0},
                {5.0, 0.0, 0.0, 1.0},
                {1.0, 0.0, 0.0, 2.0},
                {0.0, 2.0, 1.0, 0.0}
        };
        assertArrayEquals(matrizEsperada, matriz_frec);

    }

    @Test
    public void pos_valida() {
        BranchandBound b = new BranchandBound();
        boolean comprobar_1 = b.pos_valida(2,9,10,26);
        boolean comprobar_2 = b.pos_valida(2, 5, 10, 26);
        assertEquals(false, comprobar_1);
        assertEquals(true, comprobar_2);
    }

    @Test
    public void quitar_acento() {
        BranchandBound b = new BranchandBound();
        String s = b.quitarTilde('á');
        String s2 = b.quitarTilde('ü');
        assertEquals("a", s);
        assertEquals("u", s2);
    }

}
