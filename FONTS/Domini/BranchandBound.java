package Domini;

import java.util.*;

import Domini.Teclat;

public class BranchandBound implements Estrategia {
    // Atributos
    private Teclat keyboard;
    private double[][] Mat_dist; //Matriz de distancia entre las ubicaciones
    private double[][] Mat_traf;  //Matriz de tráfico entre las instalaciones
    private char[][] best_sol;  //Layout resultante del algoritmo
    private double best_cost; // mejor cota hasta el momento

    //Métodos

    class pos{
        private int x;
        private int y;

        public pos(int pos_x, int pos_y){
            this.x = pos_x;
            this.y = pos_y;
        }
    }

    class Nodo{
        private char[][] layout;
        private double cota;

        private Map<Character, pos> letres_usades;

        public Nodo(char[][] matriz, double cota, Map<Character, pos> ini) {
            this.layout = new char[matriz.length][matriz[0].length];
            for(int i = 0; i < matriz.length; i++){
                for(int j = 0; j < matriz[0].length; j++)
                    layout[i][j] = matriz[i][j];
            }
            this.cota = cota;
            letres_usades = new HashMap<>(ini);

        }
    }

    private double calcular_cota(char[][] matriz, Map<Character, pos> letres_usades, Map<Character, Integer> letra_pos){
        double termino_1 = 0.0;
        //Cálculo del valor del término 1
        for(Map.Entry<Character, pos> entry : letres_usades.entrySet()){
            char letra = entry.getKey();
            int pos_x = entry.getValue().x;
            int pos_y = entry.getValue().y;
            //recorremos las instalaciones ya colocadas y con letra distinta a la actual
            for(int i = 0; i < matriz.length; i++){
                for(int j = 0; j < matriz[0].length; j++)
                    if(matriz[i][j] != ' ' && matriz[i][j] != letra){
                        //frecuencia entre la letra actual y la letra en la instalación actual
                        double frecuencia = Mat_traf[letra_pos.get(letra)][letra_pos.get(matriz[i][j])];
                        // distancia entre la letra actual y la letra en la instalación actual
                        double distancia = Mat_dist[(pos_x * matriz[0].length) + pos_y][(i * matriz[0].length) + j];
                        termino_1 += frecuencia * distancia;
                    }
            }
        }
        //Cálculo de la matriz C1
        double[][] Mat_c1;
        int m = letra_pos.size() - letres_usades.size(); // número de letras sin utilizar
        if(m != 0){
            Mat_c1 = new double[m][m];
            int letra_num, posicion;
            letra_num = posicion = 0;
            for(Map.Entry<Character, Integer> entry : letra_pos.entrySet() ){
                char letra_actual = entry.getKey();
                if(!letres_usades.containsKey(letra_actual)){
                    for(int i = 0; i < matriz.length; i++){
                        for(int j = 0; j < matriz[0].length; j++) {
                            if (matriz[i][j] == ' ') {
                                double suma = 0.0;
                                for (Map.Entry<Character, pos> entry_2 : letres_usades.entrySet()) {
                                    char letra = entry_2.getKey();
                                    int pos_x = entry_2.getValue().x;
                                    int pos_y = entry_2.getValue().y;
                                    //frecuencia entre la letra actual y la letra usada en la que estamos
                                    double frecuencia = Mat_traf[letra_pos.get(letra_actual)][letra_pos.get(letra)];
                                    // distancia entre la posicion de la letra actual y la letra usada en su instalación
                                    double distancia = Mat_dist[(i * matriz[0].length) + j][(pos_x * matriz[0].length) + pos_y];
                                    suma += frecuencia * distancia;
                                }
                                Mat_c1[letra_num][posicion] = suma;
                                ++posicion;
                            }
                        }
                    }
                    ++letra_num;
                }
            }
        }

        //Cálculo de la matriz C2

        return termino_1;
    }
    class NodoComparator implements Comparator<Nodo> {

        @Override
        public int compare(Nodo o1, Nodo o2) {
            return Double.compare(o1.cota, o2.cota);
        }
    }

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
        Nodo nodo_inicial = new Nodo(matriz, best_cost, ini);
        q.add(nodo_inicial);
        while(!q.isEmpty() && !finalizar){
            Nodo nodo_actual = q.poll();
            if(nodo_actual.letres_usades.size() == n){
                //es la solución óptima
                this.best_sol = nodo_actual.layout;
                finalizar = true;
            }
            else{
                for(char letra : lletres) {
                    if(!nodo_actual.letres_usades.containsKey(letra)) {
                        for (int i = 0; i < n_filas; i++) {
                            for (int j = 0; j < n_columnas; j++) {
                                if (nodo_actual.layout[i][j] == ' ') {
                                    Nodo nuevo = new Nodo(nodo_actual.layout, nodo_actual.cota, nodo_actual.letres_usades);
                                    nuevo.layout[i][j] = letra;
                                    pos p = new pos(i, j);
                                    nuevo.letres_usades.put(letra, p);
                                    double cota = calcular_cota(nuevo.layout, nuevo.letres_usades, letra_pos);
                                    if (cota < best_cost) {
                                        nuevo.cota = cota;
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
    private double[][] calculaMatTraf(Map<String, Integer> palabrasFrec, Map<Character, Integer> lletres, int n) {


        //inicializada a 0 por defecto
        double[][] trafficMatrix = new double[n][n];

        for (String palabra : palabrasFrec.keySet()) {
            int frecuencia = palabrasFrec.get(palabra);

            for (int i = 0; i < palabra.length() - 1; i++) {
                //guardamos los índices de cada letra en el abecedario
                int letra1 = lletres.get(palabra.charAt(i));
                int letra2 = lletres.get(palabra.charAt(i + 1));

                trafficMatrix[letra1][letra2] += frecuencia;
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


   @Override
    public void solve(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {
        //declaramos una solucion inicial
        this.best_sol = new char[n_filas][n_columnas];
        //le ponemos valor = infinito a la cota inicial
        this.best_cost = Double.POSITIVE_INFINITY;

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
    }

}
