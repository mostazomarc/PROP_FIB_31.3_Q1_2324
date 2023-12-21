package Domini;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.Normalizer;
import Domini.Teclat;
import Domini.HungarianAlgorithm;
import Domini.Nodo;
import Domini.pos;
import Domini.NodoComparator;
import Domini.Greedy;

/**
 * Branchandbound és una subclasse de Estratègia que conté l'algoritme branch and bound per calcular la disposició del teclat
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */

public class BranchandBound extends Estrategia {
    // Atributos
    //private Teclat keyboard;

    /**
     * Matriu de distàncies de les posicions del teclat
     */
    private double[][] Mat_dist;

    /**
     * Matriu de freqüències de les lletres del abecedari
     */
    private double[][] Mat_traf;

    /**
     * Matriu on es guarda el resultat final
     */
    private char[][] best_sol;

    //Métodos

    /**
     * Mètode que realitza el algoritme branch and bound
     * @param n_filas el nombre de files del teclat
     * @param n_columnas el nombre de columnes del teclat
     * @param lletres Les lletres del abecedari
     * @param letra_pos cada lletra amb la seva posició a les matrius
     */
    private void algoritm_bab(int n_filas, int n_columnas, Set<Character> lletres, Map<Character, Integer> letra_pos){
        PriorityQueue<Nodo> q = new PriorityQueue<>(new NodoComparator());
        boolean finalizar = false;
        int n = lletres.size();
        char[][] matriz = new char[n_filas][n_columnas];
        for(int i = 0; i < n_filas; i++) {
            for(int j = 0; j < n_columnas; j++)
                matriz[i][j] = ' ';
        }
        Map <Character, pos> ini = new HashMap<>();
        //Nodo nodo_inicial = new Nodo(matriz, 0, ini);
        Greedy g = new Greedy();
        Nodo nodo_inicial = g.solucion_inicial(Mat_traf, Mat_dist, n_filas, n_columnas, lletres);
        nodo_inicial.calcular_cota( letra_pos, n_columnas, Mat_dist, Mat_traf);

        q.add(nodo_inicial);
        while(!q.isEmpty() && !finalizar){
            Nodo nodo_actual = q.poll();
            if(nodo_actual.letres_usades.size() == n){
                //es la solución óptima
                this.best_sol = nodo_actual.layout;
                finalizar = true;
            }
            else{
                boolean espera = false;
                Iterator<Map.Entry<Character, Integer>> iterator = letra_pos.entrySet().iterator();
                char letra = ' ';
                while(!espera){
                    Map.Entry<Character, Integer> entry = iterator.next();
                    letra = entry.getKey();
                    if(!nodo_actual.letres_usades.containsKey(letra))
                        espera = true;
                }

                double mejor_cost = Double.POSITIVE_INFINITY;
                char[][] mat = new char[n_filas][n_columnas];
                Map<Character, pos> inicial = new HashMap<>();
                Nodo el_mejor = new Nodo(mat , 0, inicial);

                for (int i = 0; i < n_filas; i++) {
                    for (int j = 0; j < n_columnas; j++) {
                        if (nodo_actual.layout[i][j] == ' ' && pos_valida(i, j, n_columnas, letra_pos.size())) {
                            Nodo nuevo = new Nodo(nodo_actual.layout, nodo_actual.cota, nodo_actual.letres_usades);
                            nuevo.layout[i][j] = letra;
                            pos p = new pos(i, j);
                            nuevo.letres_usades.put(letra, p);
                            nuevo.calcular_cota( letra_pos, n_columnas, Mat_dist, Mat_traf);
                            if (nuevo.cota < mejor_cost) {
                                mejor_cost = nuevo.cota;
                                el_mejor = nuevo;
                            }
                        }
                    }
                }
                q.add(el_mejor);
            }
        }

    }

    /**
     * Mètode que calcula la disposició del teclat
     * @param palabrasFrec les paraules amb les seves freqüències
     * @param lletres les lletres del abecedari
     * @param n_filas el nombre de files del teclat
     * @param n_columnas el nombre de columnes del teclat
     * @return la matriu de disposició resultant de l'algoritme Branch and Bound
     */
    @Override
    public char[][] calculaDisposicio(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {
        //declaramos una solucion inicial
        this.best_sol = new char[n_filas][n_columnas];
        //le ponemos valor = infinito a la cota inicial

        int n = lletres.size();
        int i = 0;
        //Map de cada letra con su índice
        Map<Character, Integer> letra_pos = new HashMap<>();
        for(char letra : lletres){
            letra_pos.put(letra, i);
            ++i;
        }

        //Inicializamos la matriz de tráfico
        Mat_traf = calculaMatTraf(palabrasFrec, letra_pos, n);
        //Inicializamos la matriz de distancias
        Mat_dist = calculaMatDist(n, n_filas, n_columnas);
        
        // realizamos el algoritmo Branch and Bound
        this.algoritm_bab(n_filas, n_columnas, lletres, letra_pos);

        return this.best_sol;
    }
}

//Francisco Torredemer
