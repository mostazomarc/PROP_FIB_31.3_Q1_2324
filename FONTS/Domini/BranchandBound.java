package Domini;

import java.util.*;

import Domini.Teclat;
import Domini.HungarianAlgorithm;

public class BranchandBound implements Estrategia {
    // Atributos
    private Teclat keyboard;
    private double[][] Mat_dist; //Matriz de distancia entre las ubicaciones
    private double[][] Mat_traf;  //Matriz de tráfico entre las instalaciones
    private char[][] best_sol;  //Layout resultante del algoritmo

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

    public double[][] sumaMatrices(double[][] mat1, double[][] mat2) {

        double[][] suma = new double[mat1.length][mat1[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                suma[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        return suma;
    }

    private double calcular_cota(char[][] matriz, Map<Character, pos> letres_usades, Map<Character, Integer> letra_pos, int n_columnas){
        double termino_1 = 0.0;
        //Cálculo del valor del término 1
        for(Map.Entry<Character, pos> entry : letres_usades.entrySet()){
            char letra = entry.getKey();
            int pos_x = entry.getValue().x;
            int pos_y = entry.getValue().y;
            //recorremos las instalaciones ya colocadas y con letra distinta a la actual
            for(int i = 0; i < matriz.length; i++){
                for(int j = 0; j < matriz[0].length; j++)
                    if(matriz[i][j] != ' ' && matriz[i][j] != letra && pos_valida(i, j, n_columnas, letra_pos.size())){
                        //frecuencia entre la letra actual y la letra en la instalación actual
                        double frecuencia = Mat_traf[letra_pos.get(letra)][letra_pos.get(matriz[i][j])];
                        // distancia entre la letra actual y la letra en la instalación actual
                        double distancia = Mat_dist[(pos_x * matriz[0].length) + pos_y][(i * matriz[0].length) + j];
                        termino_1 += frecuencia * distancia;
                    }
            }
        }
        ArrayList<pos> pos_libres = new ArrayList<>();
        ArrayList<Character> letras_libres = new ArrayList<>();

        double[][] Mat_c1, Mat_c2;
        double coste_termino_2 = 0.0;
        int m = letra_pos.size() - letres_usades.size(); // número de letras sin utilizar

        if(m != 0){
            Mat_c1 = new double[m][m];
            Mat_c2 = new double[m][m];

            //Cálculo de la matriz C1

            int letra_num, posicion;
            letra_num = posicion = 0;
            for(Map.Entry<Character, Integer> entry : letra_pos.entrySet() ){
                char letra_actual = entry.getKey();
                if(!letres_usades.containsKey(letra_actual)){
                    letras_libres.add(letra_actual);
                    for(int i = 0; i < matriz.length; i++){
                        for(int j = 0; j < matriz[0].length; j++) {
                            if (matriz[i][j] == ' ' && pos_valida(i, j, n_columnas, letra_pos.size())) {
                                if(pos_libres.size() != m){
                                    pos p = new pos(i, j);
                                    pos_libres.add(p);
                                }
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
                    posicion = 0;
                }
            }



            //Cálculo de la matriz C2

            //recorremos las letras libres(sin usar)
            for(int i = 0; i < m; ++i){

                //CÁCULO DEL VECTOR T

                PriorityQueue<Double> T = new PriorityQueue<>();
                //recorremos el resto de letras/instalaciones no emplazadas
                for(int h = 0; h < m; ++h){
                    if(h != i){
                        T.add(Mat_traf[letra_pos.get(letras_libres.get(i))][letra_pos.get(letras_libres.get(h))]);
                    }
                }
                //recorremos las posiciones libres
                for(int j = 0; j < m; ++j){
                    PriorityQueue<Double> D = new PriorityQueue<>(Comparator.reverseOrder());

                    //CÁCULO DEL VECTOR D

                    //recorremos las ubicaciones no ocupadas
                    for(int h = 0; h < m; ++h){
                        if(j != h){
                            pos ubicacion_actual = pos_libres.get(j);
                            pos ubicacion_resto = pos_libres.get(h);
                            D.add(Mat_dist[(ubicacion_actual.x * matriz[0].length) + ubicacion_actual.y][(ubicacion_resto.x * matriz[0].length) + ubicacion_resto.y]);
                        }
                    }

                    //Realizamos el producto escalar de T y D

                    Iterator<Double> iterator_T = T.iterator();
                    Iterator<Double> iterator_D = D.iterator();
                    double acumulado = 0.0;
                    while(iterator_T.hasNext()){
                        Double elemento_T = iterator_T.next();
                        Double elemento_D = iterator_D.next();
                        acumulado += elemento_T * elemento_D;
                    }

                    //asignamos el valor del producto escalar de D y T a la letra no emplazada i y la ubicación j
                    Mat_c2[i][j] = acumulado;
                }
            }

            //Sumamos las 2 matrices C1 y C2

            double[][] suma = sumaMatrices(Mat_c1, Mat_c2);

            //resolvemos el coste de la asignación lineal óptima de c1 + c2 mediante algoritmo hungarian
            HungarianAlgorithm ha = new HungarianAlgorithm(suma);
            double valoroptim = ha.trobarAssignacioOptima();
            coste_termino_2 = valoroptim;
        }

        return termino_1 + coste_termino_2;
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
        Nodo nodo_inicial = new Nodo(matriz, 0, ini);
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
                            double cota = calcular_cota(nuevo.layout, nuevo.letres_usades, letra_pos, n_columnas);
                            if (cota < mejor_cost) {
                                mejor_cost = cota;
                                nuevo.cota = cota;
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
            System.out.println(palabra);
            palabra = palabra.toLowerCase();
            System.out.println(palabra);
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
