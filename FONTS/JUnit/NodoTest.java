package JUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import Domini.*;


import java.util.*;

public class NodoTest {

    public char[][] layout;

    public Map<Character, pos> letres_usades = new HashMap<>();

    public Map<Character, Integer> letra_pos = new HashMap<>();
    int n_filas, n_columnas;

    double Mat_traf[][];
    double mat_dist[][];

    ArrayList<pos> pos_libres = new ArrayList<>();
    ArrayList<Character> letras_libres = new ArrayList<>();

    @Before
    public void omplir_layout(){
        layout = new char[][]{
                {'a',' '},
                {' ','h'}
        };
    }

    @Before
    public void omplir_arrays(){
        pos p = new pos(0,1);
        pos p1 = new pos(1,0);
        pos_libres.add(p);
        pos_libres.add(p1);

        letras_libres.add('l');
        letras_libres.add('o');
    }

    @Before
    public void omplirFilas_Columnas() {
        n_filas = 2;
        n_columnas = 2;
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
    }

    @Before
    public void omplir_letres_usades(){
        pos p = new pos(0,0);
        letres_usades.put('a', p);

        pos p1 = new pos(1,1);
        letres_usades.put('h', p1);
    }

    @Before
    public void omplir_letres_pos(){
        letra_pos.put('a', 0);
        letra_pos.put('h', 1);
        letra_pos.put('l', 2);
        letra_pos.put('o', 3);
    }



    @Test
    public void creadora_nodos(){
        double num = 5.0;
        Nodo n = new Nodo(layout, num, letres_usades);
        double delta = 0.0;
        assertEquals(5.0, n.cota, delta);
        assertEquals(letres_usades, n.letres_usades);
        assertArrayEquals(layout, n.layout);
    }

    @Test
    public void suma_matrices(){
        Nodo n = new Nodo(layout, 0.0, letres_usades);
        double[][] matriz1 = {{5.0, 5.0, 5.0}, {5.0, 5.0, 5.0}, {5.0, 5.0, 5.0}};
        double[][] matriz2 = {{3.0, 3.0, 3.0}, {3.0, 3.0, 3.0}, {3.0, 3.0, 3.0}};
        double[][] matriz_esperada = {{8.0, 8.0, 8.0}, {8.0, 8.0, 8.0}, {8.0, 8.0, 8.0}};

        double[][] matriz_resultado = n.sumaMatrices(matriz1, matriz2);
        assertArrayEquals(matriz_esperada, matriz_resultado);

    }

    @Test
    public void calculo_termino_1(){
        Nodo n = new Nodo(layout, 0.0, letres_usades);
        double termino_1 = n.calcular_termino_1(letra_pos, n_columnas, mat_dist, Mat_traf);
        double delta = 0.000001;

        assertEquals(7.0710678118654755, termino_1, delta);

    }

    @Test
    public void calculo_matC1(){
        Nodo n = new Nodo(layout, 0.0, letres_usades);
        int m = letra_pos.size() - this.letres_usades.size();
        double[][] C1 = n.calculo_C1(m, pos_libres, letras_libres, letra_pos, n_columnas, mat_dist, Mat_traf);

        double[][] matriz_esperada = {
                {3.0,3.0},
                {3.0,3.0}
        };

        assertArrayEquals(matriz_esperada, C1);

    }

    @Test
    public void calculo_matC2(){
        Nodo n = new Nodo(layout, 0.0, letres_usades);
        int m = letra_pos.size() - this.letres_usades.size();
        double[][] C2 = n.calculo_C2(m, pos_libres, letras_libres, letra_pos, mat_dist, Mat_traf);

        double[][] matriz_esperada = {
                {2.8284271247461903,2.8284271247461903},
                {1.4142135623730951,1.4142135623730951}
        };

        assertArrayEquals(matriz_esperada, C2);

    }

    @Test
    public void calculo_termino_2(){
        Nodo n = new Nodo(layout, 0.0, letres_usades);
        int m = letra_pos.size() - this.letres_usades.size();
        double termino_2 = n.calcular_termino_2(m,  letra_pos, n_columnas,mat_dist, Mat_traf);
        double delta = 0.000001;

        assertEquals(10.242640687119284, termino_2, delta);

    }


}

//Francisco Torredemer
