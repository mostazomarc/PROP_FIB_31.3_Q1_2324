package Domini;

import java.util.*;

public class GeneticAlgorithm implements Estrategia {

    private char[][] best_sol; //Layout resultante del algoritmo

    private static char[][] algoritm_gen(int numGeneraciones, int numPoblacion, Map<String, Integer> palabrasFreq, Set<Character> lletres, int n, int m) {
        char[][][] poblacion = new char[numPoblacion][n][m];

        for (int i = 0; i < numPoblacion; i++) {
            poblacion[i] = generarDisposicionTeclado(lletres, n, m);
        }

        for (int generacion = 0; generacion < numGeneraciones; ++generacion) {
            // Ordenar la población por aptitud (de mayor a menor);
            List<char[][]> poblacionList = new ArrayList<>();
            for (char[][] disposicion : poblacion) poblacionList.add(disposicion);

            poblacionList.sort((a,b)->Integer.compare(evaluarAptitud(b), evaluarAptitud(a)));

            // Conservar el 10% de la población élite
            List<char[][]> elite = poblacionList.subList(0, (int) (numPoblacion * 0.1));

            // Crear nuevos hijos mediante cruzamiento y mutación
            List<char[][]> nuevosHijos = new ArrayList<>();

            while (nuevosHijos.size() < numPoblacion - elite.size()) {
                char[][] padre1 = poblacionList.get(new Random().nextInt(numPoblacion));
                char[][] padre2 = poblacionList.get(new Random().nextInt(numPoblacion));
                char[][] hijo = cruzar(padre1, padre2, n, m);

                //Probabilidad de mutación del 10%
                if (new Random().nextDouble() < 0.1) mutar(hijo, n, m);

                nuevosHijos.add(hijo);
            }

            // Actualizar la población con la élite y los nuevos hijos
            poblacionList.clear();
            poblacionList.addAll(elite);
            poblacionList.addAll(nuevosHijos);

            // Convertir la lista de disposiciones a un arreglo de tres dimensiones
            poblacion = poblacionList.toArray(new char[0][0][0]);
        }

        return poblacion[0];
    }

    private static char[][] generarDisposicionTeclado(Set<Character> lletres, int n, int m) {
        List<Character> lletresList = new ArrayList<>(lletres);
        Collections.shuffle(lletresList);

        char[][] disposicionTeclado = new char[n][m];
        int index = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                disposicionTeclado[i][j] = lletresList.get(index);
                ++index;
            }
        }

        return disposicionTeclado;
    }

    private static int evaluarAptitud(char[][] disposicionTeclado) {
        int aptitud = 0;
        // IMPLEMENTAR FUNCIÓN APTITUD
        return aptitud;
    }

    private static char[][] cruzar(char[][] disposicionPadre1, char[][] disposicionPadre2, int n, int m) {
        int puntoCorte = new Random().nextInt(n);

        char[][] hijo = new char[n][m];

        for (int i = 0; i < puntoCorte; ++i) System.arraycopy(disposicionPadre1[i], 0, hijo[i], 0, m);

        for (int i = puntoCorte; i < n; ++i) System.arraycopy(disposicionPadre2[i], 0, hijo[i], 0, m);

        return hijo;
    }

    private static void mutar(char[][] disposicionTeclado, int n, int m) {
        int fila1 = new Random().nextInt(n);
        int columna1 = new Random().nextInt(m);
        int fila2 = new Random().nextInt(n);
        int columna2 = new Random().nextInt(m);

        char temp = disposicionTeclado[fila1][columna1];
        disposicionTeclado[fila1][columna1] = disposicionTeclado[fila2][columna2];
        disposicionTeclado[fila2][columna2] = temp;
    }

    @Override
    public char[][] calculaDisposicio(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {
        int numGeneraciones = 100;
        int numPoblacion = 50;

        this.best_sol = algoritm_gen(numGeneraciones, numPoblacion, palabrasFrec, lletres, n_filas, n_columnas);

        return this.best_sol;

    }
}
