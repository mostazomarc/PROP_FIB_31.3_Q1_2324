package JUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import Domini.*;


import java.util.*;

public class NodoTest {

    public char[][] layout;

    public Map<Character, pos> letres_usades;

    @Before
    public void omplir_layout(){
        layout = new char[][]{

        };
    }

    @Before
    public void omplir_letres_usades(){
    }

    @Test
    public void creadora_nodos(){
        double num = 5.0;
        Nodo n = new Nodo(layout, num, letres_usades);
        assertEquals(5.0, n.cota);
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



}
