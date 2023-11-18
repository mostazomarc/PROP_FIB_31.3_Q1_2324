package Domini;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
public class HungarianAlgorithm {
    double[][] matriu; // matriu inicial (matriu de costos)
    double [][] copiamatriu;
    // vectors copiamatriuiliars per construir la matriu
    public int[] zeroFila;
    public int[] zeroColumna;
    int[] filaCoberta;
    public int[] columnaCoberta;
    int[] zerosEstrellaEnFila;
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
        copiamatriu = new double [matriu.length][matriu.length];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu.length; ++j) {
                copiamatriu[i][j] = matriu[i][j];
            }
        }
        this.matriu = matriu;

        zeroFila = new int[matriu.length];       // zeroFila & zeroColumna indiquen la posició
        zeroColumna = new int[matriu[0].length]; // dels zeros marcats

        filaCoberta = new int[matriu.length];      // indica si una fila està coberta
        columnaCoberta = new int[matriu[0].length]; // indica si una columna està coberta
        zerosEstrellaEnFila = new int[matriu.length];  // emmagatzema els 0*
        Arrays.fill(zerosEstrellaEnFila, -1);
        Arrays.fill(zeroFila, -1);
        Arrays.fill(zeroColumna, -1);
    }

    //Pre:
    //Post: retorna el valor òptim
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
            if (zeroFila[zeroPrincipal[0]] == -1) {
                // no hi ha cap zero marcat a la fila zeroPrincipal
                pas6(zeroPrincipal);
                pas3();    // cobreix les columnes que contenen un zero marcat
            } else {
                // hi ha un zero marcat a la fila zeroPrincipal
                // pas 5
                filaCoberta[zeroPrincipal[0]] = 1;  // cobreix la fila de zeroPrincipal
                columnaCoberta[zeroFila[zeroPrincipal[0]]] = 0;  // descobreix la columna de zeroPrincipal
                pas7();
            }
        }
        int[][] assignacioOptima = new int[matriu.length][];
        for (int i = 0; i < zeroColumna.length; i++) {
            assignacioOptima[i] = new int[]{i, zeroColumna[i]};
        }
        for (int i = 0; i < assignacioOptima.length; i++) {
            valoroptim += copiamatriu[assignacioOptima[i][1]][assignacioOptima[i][0]];
        }

        return valoroptim;
    }

    //Pre:
    //Post: retorna true si totes les columnes estan cobertes, false en cas contrari
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
    public void pas1() {
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
     * Marca cada 0 que troba, si no hi ha altres zeros marcats a la mateixa fila o columna
     */
    public void pas2() {
        int[] filaTeZero = new int[matriu.length];
        int[] columnaTeZero = new int[matriu[0].length];

        for (int i = 0; i < matriu.length; i++) {
            for (int j = 0; j < matriu.length; j++) {
                // marca si el valor actual == 0 i no hi ha altres zeros marcats a la mateixa fila o columna
                if (matriu[i][j] == 0.0 && filaTeZero[i] == 0 && columnaTeZero[j] == 0) {
                    filaTeZero[i] = 1;
                    columnaTeZero[j] = 1;
                    zeroFila[i] = j; // guarda la posició de la fila del zero
                    zeroColumna[j] = i; // guarda la posició de la columna del zero
                    continue; // salta a la següent fila
                }
            }
        }
    }

    /**
     * Pas 3:
     * Cobreix totes les columnes que tenen un 0
     */
    public void pas3() {
        for (int i = 0; i < zeroColumna.length; i++) {
            columnaCoberta[i] = zeroColumna[i] != -1 ? 1 : 0;
        }
    }

    /**
     * Pas 4:
     * Troba el 0 i  el marca com a "0*". Retorna la posicio del 0*
     */
    private int[] pas4() {
        for (int i = 0; i < matriu.length; i++) {
            if (filaCoberta[i] == 0) {
                for (int j = 0; j < matriu[i].length; j++) {
                    if (matriu[i][j] == 0.0 && columnaCoberta[j] == 0) {
                        zerosEstrellaEnFila[i] = j; // marca com 0*
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Pas 6:
     * Crea una cadena K de "Zeros" i "0*" zeroPrincipal => primer zero no cobert
     */
    private void pas6(int[] zeroPrincipal) {
        int i = zeroPrincipal[0];
        int j = zeroPrincipal[1];

        Set<int[]> K = new LinkedHashSet<>();
        K.add(zeroPrincipal);
        boolean trobat = false;
        do {
            if (zeroColumna[j] != -1) {
                K.add(new int[]{zeroColumna[j], j});
                trobat = true;
            }
            else trobat = false;
            if (!trobat) {break;}

            i = zeroColumna[j];
            j = zerosEstrellaEnFila[i];
            if (j != -1) {
                K.add(new int[]{i, j});
                trobat = true;
            }
            else trobat = false;
        }
        while (trobat); //sempre que no es trobin nous zeros marcats
        for (int[] zero : K) {
            // eliminar tots els marcats "Zero" a K
            if (zeroColumna[zero[1]] == zero[0]) {
                zeroColumna[zero[1]] = -1;
                zeroFila[zero[0]] = -1;
            }
            // substitueix els marcats 0* a K amb marcats "Zero"
            if (zerosEstrellaEnFila[zero[0]] == zero[1]) {
                zeroFila[zero[0]] = zero[1];
                zeroColumna[zero[1]] = zero[0];
            }
        }
        // eliminar tots els marcats
        Arrays.fill(zerosEstrellaEnFila, -1);
        Arrays.fill(filaCoberta, 0);
        Arrays.fill(columnaCoberta, 0);
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
}

