package Domini;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map;
import Domini.Teclat;

public class BranchandBound implements Estrategia {
    // Atributos
    private Teclat keyboard;
    private double[][] Mat_dist;
    private double[][] Mat_traf;
    private char[][] best_sol;
    private double best_cost;

    //Métodos

    class Nodo{
        private char[][] layout;
        private double cota;

        private Set<Character> letres_usades;

        public Nodo(char[][] matriz, double cota,Set<Character> ini) {
            layout = matriz;
            this.cota = cota;
            letres_usades = new HashSet<>(ini);

        }
    }

    private boolean es_solucion(char[][] matriz){
        return false;
    }

    private double calcular_cota(char[][] matriz){
        return 1;
    }

    public Nodo clonarNodo(Nodo nodoOriginal) {
        if (nodoOriginal == null) {
            return null;
        }

        Nodo nuevoNodo = new Nodo(nodoOriginal.layout, nodoOriginal.cota, nodoOriginal.letres_usades);

        return nuevoNodo;
    }

    private void algoritm_bab(int n_filas, int n_columnas, Set<Character> lletres){
        PriorityQueue<Nodo> q = new PriorityQueue<>();
        boolean finalizar = false;
        char[][] matriz = new char[n_filas][n_columnas];
        for(int i = 0; i < n_filas; i++) {
            for(int j = 0; j < n_columnas; j++)
                matriz[i][j] = ' ';
        }
        Set<Character> ini = new HashSet<>();
        Nodo nodo_inicial = new Nodo(matriz, best_cost, ini);
        q.add(nodo_inicial);
        while(!q.isEmpty() && !finalizar){
            Nodo nodo_actual = q.poll();
            if(es_solucion(nodo_inicial.layout)){
                //es la solución óptima
                this.best_sol = nodo_actual.layout;
                finalizar = true;
            }
            else{
                for(char letra : lletres) {
                    if(!nodo_actual.letres_usades.contains(letra)) {
                        for (int i = 0; i < n_filas; i++) {
                            for (int j = 0; j < n_columnas; j++) {
                                if (nodo_actual.layout[i][j] == ' ') {
                                    Nodo nuevo = clonarNodo(nodo_actual);
                                    nuevo.layout[i][j] = letra;
                                    double cota = calcular_cota(nuevo.layout);
                                    if (cota < best_cost) {
                                        nuevo.cota = cota;
                                        nuevo.letres_usades.add(letra);
                                        q.add(nuevo);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    //Inicializa la matriz de tráfico
    private double[][] calculaMatTraf(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n) {


        //inicializada a 0 por defecto
        double[][] trafficMatrix = new double[n][n];

        for (String palabra : palabrasFrec.keySet()) {
            int frecuencia = palabrasFrec.get(palabra);

            for (int i = 0; i < palabra.length() - 1; i++) {
                //guardamos los índices de cada letra en el abecedario
                /*int letra1 = lletres.indexOf(palabra.charAt(i));
                int letra2 = lletres.indexOf(palabra.charAt(i + 1));

                trafficMatrix[letra1][letra2] += frecuencia;

            }
        }

        return trafficMatrix;
    }

    //Inicializa la matriz de distancias
    private double[][] calculaMatDist(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n, int n_filas, int n_columnas) {
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


    public void solve(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {
        //declaramos una solucion inicial
        this.best_sol = new char[n_filas][n_columnas];
        //le ponemos valor = infinito
        this.best_cost = Double.POSITIVE_INFINITY;

        int n = lletres.size();

        //Inicializamos la matriz de tráfico
        Mat_traf = calculaMatTraf(palabrasFrec, lletres, n);
        //Inicializamos la matriz de distancias
        Mat_dist = calculaMatDist(palabrasFrec, lletres, n, n_filas, n_columnas);

        this.algoritm_bab(n_filas, n_columnas, lletres);
    }

}
