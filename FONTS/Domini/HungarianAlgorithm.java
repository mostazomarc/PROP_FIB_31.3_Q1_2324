package Domini;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class HungarianAlgorithm {

    double[][] matriu; // matriu inicial (matriu de costos)
    double [][] aux;
    // vectors auxiliars per construir la matriu
    int[] quadratInFila, quadratInColumna, filaCoberta, columnaCoberta, zerosAstarEnFila;

    double valoroptim;
    public HungarianAlgorithm(double[][] matriu) {
        if (matriu.length != matriu[0].length) {
            try {
                throw new IllegalAccessException("La matriu no és quadrada!");
            } catch (IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }
        valoroptim = 0;

        aux = new double [matriu.length][matriu.length];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu.length; ++j) {
                aux[i][j] = matriu[i][j];
            }
        }

        this.matriu = matriu;

        quadratInFila = new int[matriu.length];       // quadratInFila & quadratInColumna indiquen la posició
        quadratInColumna = new int[matriu[0].length]; // dels zeros marcats

        filaCoberta = new int[matriu.length];      // indica si una fila està coberta
        columnaCoberta = new int[matriu[0].length]; // indica si una columna està coberta
        zerosAstarEnFila = new int[matriu.length];  // emmagatzema els 0*
        Arrays.fill(zerosAstarEnFila, -1);
        Arrays.fill(quadratInFila, -1);
        Arrays.fill(quadratInColumna, -1);
    }

    /**
     * Troba una assignació òptima
     *
     * @return assignació òptima
     */
    public double trobarAssignacioOptima() {
        pas1();    // redueix la matriu
        pas2();    // marca els zeros independents
        pas3();    // cobreix les columnes que contenen un zero marcat

        while (!totesLesColumnesEstanCobertes()) {
            int[] zeroPrincipal = pas4();
            while (zeroPrincipal == null) {      // mentre no es trobi cap zero a pas4
                pas7();
                zeroPrincipal = pas4();
            }
            if (quadratInFila[zeroPrincipal[0]] == -1) {
                // no hi ha cap zero marcat a la fila zeroPrincipal
                pas6(zeroPrincipal);
                pas3();    // cobreix les columnes que contenen un zero marcat
            } else {
                // hi ha un zero marcat a la fila zeroPrincipal
                // pas 5
                filaCoberta[zeroPrincipal[0]] = 1;  // cobreix la fila de zeroPrincipal
                columnaCoberta[quadratInFila[zeroPrincipal[0]]] = 0;  // descobreix la columna de zeroPrincipal
                pas7();
            }
        }

        int[][] assignacioOptima = new int[matriu.length][];
        for (int i = 0; i < quadratInColumna.length; i++) {
            assignacioOptima[i] = new int[]{i, quadratInColumna[i]};
        }
        for (int i = 0; i < assignacioOptima.length; i++) {
            valoroptim += aux[assignacioOptima[i][1]][assignacioOptima[i][0]];
        }

        return valoroptim;
    }

    /**
     * Comprova si totes les columnes estan cobertes. Si és així, llavors la
          * solució òptima s'ha trobat
     *
     * @return true o false
     */
    private boolean totesLesColumnesEstanCobertes() {
        for (int i : columnaCoberta) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pas 1:
     * Redueix la matriu de manera que en cada fila i columna hi hagi com a mínim un zero:
     * 1. resta la mínima de cada fila a cada element de la fila
     * 2. resta la mínima de cada columna a cada element de la columna
     */
    private void pas1() {
        // files
        for (int i = 0; i < matriu.length; i++) {
            // troba el valor mínim de la fila actual
            double minActualFila = Double.MAX_VALUE;
            for (int j = 0; j < matriu[i].length; j++) {
                if (matriu[i][j] < minActualFila) {
                    minActualFila = matriu[i][j];
                }
            }
            // resta el valor mínim a cada element de la fila actual
            for (int k = 0; k < matriu[i].length; k++) {
                matriu[i][k] -= minActualFila;
            }
        }

        // columnes
        for (int i = 0; i < matriu[0].length; i++) {
            // troba el valor mínim de la columna actual
            double minActualColumna = Double.MAX_VALUE;
            for (int j = 0; j < matriu.length; j++) {
                if (matriu[j][i] < minActualColumna) {
                    minActualColumna = matriu[j][i];
                }
            }
            // resta el valor mínim a cada element de la columna actual
            for (int k = 0; k < matriu.length; k++) {
                matriu[k][i] -= minActualColumna;
            }
        }
    }

    /**
     * Pas 2:
     * Marca cada 0 amb un "quadrat", si no hi ha altres zeros marcats a la mateixa fila o columna
     */
    private void pas2() {
        int[] filaTeQuadrat = new int[matriu.length];
        int[] columnaTeQuadrat = new int[matriu[0].length];

        for (int i = 0; i < matriu.length; i++) {
            for (int j = 0; j < matriu.length; j++) {
                // marca si el valor actual == 0 i no hi ha altres zeros marcats a la mateixa fila o columna
                if (matriu[i][j] == 0.0 && filaTeQuadrat[i] == 0 && columnaTeQuadrat[j] == 0) {
                    filaTeQuadrat[i] = 1;
                    columnaTeQuadrat[j] = 1;
                    quadratInFila[i] = j; // guarda la posició de la fila del zero
                    quadratInColumna[j] = i; // guarda la posició de la columna del zero
                    continue; // salta a la següent fila
                }
            }
        }
    }

    /**
     * Pas 3:
     * Cobra totes les columnes que estan marcades amb un "quadrat"
     */
    private void pas3() {
        for (int i = 0; i < quadratInColumna.length; i++) {
            columnaCoberta[i] = quadratInColumna[i] != -1 ? 1 : 0;
        }
    }

    /**
     * Pas 7:
     * 1. Troba el valor no cobert més petit de la matriu.
     * 2. Resta'l a tots els valors no coberts.
     * 3. Suma'l a tots els valors coberts dues vegades.
     */
    private void pas7() {
        // Troba el valor no cobert més petit de la matriu
        double minimValorNoCobert = Double.MAX_VALUE;
        for (int i = 0; i < matriu.length; i++) {
            if (filaCoberta[i] == 1) {
                continue;
            }
            for (int j = 0; j < matriu[0].length; j++) {
                if (columnaCoberta[j] == 0 && matriu[i][j] < minimValorNoCobert) {
                    minimValorNoCobert = matriu[i][j];
                }
            }
        }

        if (minimValorNoCobert > 0) {
            for (int i = 0; i < matriu.length; i++) {
                for (int j = 0; j < matriu[0].length; j++) {
                    if (filaCoberta[i] == 1 && columnaCoberta[j] == 1) {
                        // Suma minim a tots els valors coberts dues vegades
                        matriu[i][j] += minimValorNoCobert;
                    } else if (filaCoberta[i] == 0 && columnaCoberta[j] == 0) {
                        // Resta minim a tots els valors no coberts
                        matriu[i][j] -= minimValorNoCobert;
                    }
                }
            }
        }
    }

    /**
     * Pas 4:
     * Troba el valor zero Z_0 i marca'l com a "0*".
     *
     * @return posició de Z_0 a la matriu
     */
    private int[] pas4() {
        for (int i = 0; i < matriu.length; i++) {
            if (filaCoberta[i] == 0) {
                for (int j = 0; j < matriu[i].length; j++) {
                    if (matriu[i][j] == 0.0 && columnaCoberta[j] == 0) {
                        zerosAstarEnFila[i] = j; // marca com 0*
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Pas 6:
     * Crea una cadena K de "quadrats" i "0*"
     *
     * @param zeroPrincipal => Z_0 del pas 4
     */
    private void pas6(int[] zeroPrincipal) {
        int i = zeroPrincipal[0];
        int j = zeroPrincipal[1];

        Set<int[]> K = new LinkedHashSet<>();
        //(a)
        // afegir Z_0 a K
        K.add(zeroPrincipal);
        boolean trobat = false;
        do {
            // (b)
            // afegir Z_1 a K si
            // hi ha un zero Z_1 que està marcat amb un "quadrat" a la columna de Z_0
            if (quadratInColumna[j] != -1) {
                K.add(new int[]{quadratInColumna[j], j});
                trobat = true;
            } else {
                trobat = false;
            }

            // si no hi ha cap element zero Z_1 marcat amb "quadrat" a la columna de Z_0, llavors cancel·la el bucle
            if (!trobat) {
                break;
            }

            // (c)
            // substitueix Z_0 amb el 0* a la fila de Z_1
            i = quadratInColumna[j];
            j = zerosAstarEnFila[i];
            // afegir el nou Z_0 a K
            if (j != -1) {
                K.add(new int[]{i, j});
                trobat = true;
            } else {
                trobat = false;
            }

        } while (trobat); // (d) sempre que no es trobin nous marcats "quadrat"

        // (e)
        for (int[] zero : K) {
            // eliminar tots els marcats "quadrat" a K
            if (quadratInColumna[zero[1]] == zero[0]) {
                quadratInColumna[zero[1]] = -1;
                quadratInFila[zero[0]] = -1;
            }
            // substitueix els marcats 0* a K amb marcats "quadrat"
            if (zerosAstarEnFila[zero[0]] == zero[1]) {
                quadratInFila[zero[0]] = zero[1];
                quadratInColumna[zero[1]] = zero[0];
            }
        }

        // (f)
        // eliminar tots els marcats
        Arrays.fill(zerosAstarEnFila, -1);
        Arrays.fill(filaCoberta, 0);
        Arrays.fill(columnaCoberta, 0);
    }
}

