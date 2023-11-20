package JUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import Domini.*;


import java.util.*;

public class GreedyTest {
    int n_filas, n_columnas;
    int n_filas2, n_columnas2;

    Set<Character> conjuntoLetras = new HashSet<>();
    Set<Character> conjuntoLetras2 = new HashSet<>();

    double Mat_traf[][];
    double Mat_traf2[][];
    double mat_dist[][];
    double mat_dist2[][];

    @Before
    public void omplirFilas_Columnas() {
        n_filas = 2;
        n_columnas = 2;

        n_filas2 = 3;
        n_columnas2 = 3;
    }

    @Before
    public void omplirLetras() {
        conjuntoLetras.add('h');
        conjuntoLetras.add('o');
        conjuntoLetras.add('l');
        conjuntoLetras.add('a');

        conjuntoLetras2.add('a');
        conjuntoLetras2.add('b');
        conjuntoLetras2.add('c');
        conjuntoLetras2.add('d');
        conjuntoLetras2.add('e');
        conjuntoLetras2.add('f');
        conjuntoLetras2.add('g');
        conjuntoLetras2.add('j');
        conjuntoLetras2.add('h');
    }

    @Before
    public void llenar_mat_dist(){
        double[][] matrizdist = {
                {0.0 ,1.0, 1.0, 1.4142135623730951},
                {1.0, 0.0, 1.4142135623730951, 1.0},
                {1.0, 1.4142135623730951, 0.0, 1.0},
                {1.4142135623730951, 1.0, 1.0, 0.0}
        };
        mat_dist = matrizdist;

    double[][] matrizdist2 = {
                {0.0,1.0,2.0,1.0,1.4142135623730951,2.23606797749979,2.0,2.23606797749979,2.8284271247461903},
                {1.0,0.0,1.0,1.4142135623730951,1.0,1.4142135623730951,2.23606797749979,2.0,2.23606797749979},
                {2.0,1.0,0.0,2.23606797749979,1.4142135623730951,1.0,2.8284271247461903,2.23606797749979,2.0},
                {1.0,1.4142135623730951,2.23606797749979,0.0,1.0,2.0,1.0,1.4142135623730951,2.23606797749979},
                {1.4142135623730951,1.0,1.4142135623730951,1.0,0.0,1.0,1.4142135623730951,1.0,1.4142135623730951},
                {2.23606797749979,1.4142135623730951,1.0,2.0,1.0,0.0,2.23606797749979,1.4142135623730951,1.0},
                {2.0,2.23606797749979,2.8284271247461903,1.0,1.4142135623730951,2.23606797749979,0.0,1.0,2.0},
                {2.23606797749979,2.0,2.23606797749979,1.4142135623730951,1.0,1.4142135623730951,1.0,0.0,1.0},
                {2.8284271247461903,2.23606797749979,2.0,2.23606797749979,1.4142135623730951,1.0,2.0,1.0,0.0}
        };
        mat_dist2 = matrizdist2;

    }

    @Before
    public void llenar_mat_frec(){
        double[][] matriztraf ={
                {0.0, 0.0, 2.0, 0.0},
                {5.0, 0.0, 0.0, 1.0},
                {1.0, 0.0, 0.0, 2.0},
                {0.0, 2.0, 1.0, 0.0}
        };
        Mat_traf = matriztraf;

        double[][] matriztraf2 ={
                {40.0,40.0,0.0,0.0,20.0,22.0,20.0,0.0,0.0},
                {42.0,0.0,3.0,3.0,0.0,0.0,0.0,0.0,0.0},
                {0.0,3.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},
                {0.0,0.0,0.0,0.0,3.0,0.0,0.0,0.0,0.0},
                {20.0,0.0,0.0,0.0,0.0,3.0,0.0,0.0,0.0},
                {22.0,0.0,0.0,0.0,0.0,0.0,3.0,0.0,0.0},
                {20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,3.0},
                {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},
                {3.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0}
        };
        Mat_traf2 = matriztraf2;
    }

    @Test
    public void algoritmo_Greedy(){
        Greedy g = new Greedy();
        Nodo n = g.solucion_inicial(Mat_traf, mat_dist, n_filas, n_columnas, conjuntoLetras);
        Nodo n2 = g.solucion_inicial(Mat_traf2, mat_dist2 ,n_filas2, n_columnas2, conjuntoLetras2);
        char[][] esperado = {
            {'a',' '},
            {' ', 'h'}
        };

        char[][] esperado2 = {
                {'h',' ',' '},
                {' ','a',' '},
                {' ',' ',' '}
        };

        assertArrayEquals(esperado, n.layout);
        assertArrayEquals(esperado2, n2.layout);
    }

}
