package Domini;

import Domini.Nodo;
import Domini.pos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class Greedy {

    //Atributs

    int peor_posicion;
    int mejor_posicion;

    char mas_frec;
    char menos_frec;


    //Métodes
    public Nodo solucion_inicial(double[][] mat_traf, double[][] mat_dist, int n_filas, int n_col, Set<Character> lletres){
        char[][] matriz_inicial = new char[n_filas][n_col];
        for(int i = 0; i < n_filas; i++) {
            for(int j = 0; j < n_col; j++)
                matriz_inicial[i][j] = ' ';
        }
         peor_posicion = 0;
        mejor_posicion = 0;
        double total_mejor_pos = Double.MAX_VALUE;
        double total_peor_pos = 0.0;

        mas_frec = ' ';
        menos_frec = ' ';
        double total_peor_letra = Double.MAX_VALUE;
        double total_mejor_letra = 0.0;

        //buscamos cuales son la letra con más frecuencia y la con menos
        Iterator<Character> iterator = lletres.iterator();
        for(int i = 0; i < mat_traf.length ; ++i){
            char letra = iterator.next();
            double suma = 0.0;
            for(int j = 0; j < mat_traf[0].length; ++j){
                if(i != j)
                suma += mat_traf[i][j];
            }
            if(suma > total_mejor_letra){
                total_mejor_letra = suma;
                mas_frec = letra;
            }
            if(suma <= total_peor_letra){
                total_peor_letra = suma;
                menos_frec = letra;
            }
        }
        //buscamos cuales son la peor posicion y laa mejor posicion
        for(int i = 0; i < mat_dist.length ; ++i){
            char letra;
            double suma = 0.0;
            for(int j = 0; j < mat_dist[0].length; ++j){
                suma += mat_dist[i][j];
            }
            if(suma > total_peor_pos){
                total_peor_pos = suma;
                peor_posicion = i;
            }
            if(suma <= total_mejor_pos){
                total_mejor_pos = suma;
                mejor_posicion = i;
            }
        }

        Map<Character, pos> ini = new HashMap<>();

        //asignamos la letra con más frecuencia a la mejor posición
        matriz_inicial[mejor_posicion / n_col][mejor_posicion % n_col] = mas_frec;
        pos p = new pos(mejor_posicion / n_col, mejor_posicion % n_col);
        ini.put(mas_frec, p);

       //asignamos la letra con menor frecuencia a la peor posición
        matriz_inicial[peor_posicion / n_col][peor_posicion % n_col] = menos_frec;
        pos p1 = new pos(peor_posicion / n_col, peor_posicion % n_col);
        ini.put(menos_frec, p1);

        Nodo nodo_inicial = new Nodo(matriz_inicial, 0, ini);
        return nodo_inicial;
    }
}

//Francisco Torredemer
