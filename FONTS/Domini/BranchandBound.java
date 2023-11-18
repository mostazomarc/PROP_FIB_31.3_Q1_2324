package Domini;

import java.util.*;


import Domini.Teclat;
import Domini.HungarianAlgorithm;
import Domini.Nodo;
import Domini.pos;
import Domini.NodoComparator;
import Domini.Greedy;

public class BranchandBound implements Estrategia {
    // Atributos
    private Teclat keyboard;
    private double[][] Mat_dist; //Matriz de distancia entre las ubicaciones
    private double[][] Mat_traf;  //Matriz de tráfico entre las instalaciones
    private char[][] best_sol;  //Layout resultante del algoritmo

    //Métodos


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
    //Inicializa la matriz de tráfico
    //Inicializa la matriz de tráfico
    private double[][] calculaMatTraf(Map<String, Integer> palabrasFrec, Map<Character, Integer> lletres, int n) {


        //inicializada a 0 por defecto
        double[][] trafficMatrix = new double[n][n];

        for (String palabra : palabrasFrec.keySet()) {
            int frecuencia = palabrasFrec.get(palabra);
            palabra = palabra.toLowerCase();
            if(palabra.length() != 1) {
                for (int i = 0; i < palabra.length() - 1; i++) {
                    //guardamos los índices de cada letra en el abecedario
                    char c1 = palabra.charAt(i);
                    char c2 = palabra.charAt(i + 1);
                    if(c1 == 'à'){
                        c1 = 'a';
                    }
                    else if(c1 == 'á'){
                        c1 = 'a';
                    }
                    else if(c1 == 'è'){
                        c1 = 'e';
                    }
                    else if(c1 == 'é'){
                        c1 = 'e';
                    }
                    else if(c1 == 'ó'){
                        c1 = 'o';
                    }
                    else if(c1 == 'ò'){
                        c1 = 'o';
                    }
                    else if(c1 == 'ï'){
                        c1 = 'i';
                    }
                    else if(c1 == 'ü'){
                        c1 = 'u';
                    }
                    else if(c1 == 'ú'){
                        c1 = 'u';
                    }
                    else if(c1 == 'í'){
                        c1 = 'i';
                    }
                    else if(c1 == 'ñ'){
                        c1 = 'n';
                    }
                    else if(c1 == 'ç'){
                        c1 = 'c';
                    }

                    if(c2 == 'à'){
                        c2 = 'a';
                    }
                    else if(c2 == 'á'){
                        c2 = 'a';
                    }
                    else if(c2 == 'è'){
                        c2 = 'e';
                    }
                    else if(c2 == 'é'){
                        c2 = 'e';
                    }
                    else if(c2 == 'ó'){
                        c2 = 'o';
                    }
                    else if(c2 == 'ò'){
                        c2 = 'o';
                    }
                    else if(c2 == 'ï'){
                        c2 = 'i';
                    }
                    else if(c2 == 'ü'){
                        c2 = 'u';
                    }
                    else if(c2 == 'ú'){
                        c2 = 'u';
                    }
                    else if(c2 == 'í'){
                        c2 = 'i';
                    }
                    else if(c2 == 'ñ'){
                        c2 = 'n';
                    }
                    else if(c2 == 'ç'){
                        c2 = 'c';
                    }

                    if(c1 != '-' && c2 != '-' && c1 != '·' && c2 != '·'){
                        int letra1 = lletres.get(c1);
                        int letra2 = lletres.get(c2);
                        trafficMatrix[letra1][letra2] += frecuencia;
                    }

                }
            }
        }

        return trafficMatrix;
    }

    //Inicializa la matriz de distancias
    private double[][] calculaMatDist( int n, int n_filas, int n_columnas) {
        //inicializada a 0 por defecto
        double[][] distanceMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    double distancia;
                    //ubicaciones en la misma fila
                    if(i/n_columnas == j/n_columnas) distancia = Math.abs(j-i);
                        //ubicaciones en filas distintas
                    else{
                        //número de filas de distancia
                        double dist_filas = Math.abs(i/n_columnas - j/n_columnas);
                        //número de columnas de distancia
                        double dist_columnas = Math.abs((i % n_columnas) - (j % n_columnas));
                        //distancia según teorema de Pitágoras
                        distancia = Math.sqrt(Math.pow(dist_filas,2) + Math.pow(dist_columnas, 2));
                    }
                    distanceMatrix[i][j] = distancia;
                    distanceMatrix[j][i] = distancia;
                }
            }
        }

        return distanceMatrix;
    }

    public boolean pos_valida(Integer i, Integer j, Integer n_columnas, Integer n){
        return ((i*n_columnas) + j) < n;
    }



    @Override
    public char[][] solve(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {
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
