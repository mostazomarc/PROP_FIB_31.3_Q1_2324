package Domini;

import java.util.*;

import Domini.pos;

/**
 * Nodo és una classe que conté una disposició del teclat i la seva cota, representa cada solució parcial del algoritme Branch and Bound
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */


public class Nodo {

    /**
     * Matriu amb la disposició del node
     */
    public char[][] layout;

    /**
     * El valor que té la disposició
     */
    public double cota;

    /**
     * Mapa amb cada lletra del abecedari utilitzada a la disposició del node i la seva posició al teclat
     */
    public Map<Character, pos> letres_usades;

    /**
     * Creadora del node
     * @param matriz La matriu que igualarem el layout del node
     * @param cota El valor que igualarem la cota del node
     * @param ini Les lletres utilitzades amb la seva posició a les que igualarem al node
     */
    public Nodo(char[][] matriz, double cota, Map<Character, pos> ini) {
        this.layout = new char[matriz.length][matriz[0].length];
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++)
                layout[i][j] = matriz[i][j];
        }
        this.cota = cota;
        letres_usades = new HashMap<>(ini);

    }

    /**
     * Retorna si la posició és vàlida al teclat
     * @param i la fila de la posició
     * @param j la columna de la posició
     * @param n_columnas el nombre de columnes del teclat
     * @param n el nombre de lletres del abecedari
     * @return cert o fals segons la validesa de la posició al teclat
     */

    private boolean pos_valida(Integer i, Integer j, Integer n_columnas, Integer n){
        return ((i*n_columnas) + j) < n;
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

    /**
     * Retorna el càlcul del terme 1 de la disposició actual
     * @param letra_pos Mapa amb cada lletra amb la seva posició a les matrius de trànsit i distància
     * @param n_columnas el nombre de columnes del teclat
     * @param Mat_dist Matriu de distàncies per cada parell de posicions al teclat
     * @param Mat_traf Matriu de freqüències per cada parell de lletres al abecedari
     * @return El terme 1 calculat com double
     */

    public double calcular_termino_1(Map<Character, Integer> letra_pos, int n_columnas, double[][] Mat_dist, double[][] Mat_traf){
        double termino_1 = 0.0;
        for(Map.Entry<Character, pos> entry : this.letres_usades.entrySet()){
            char letra = entry.getKey();
            int pos_x = entry.getValue().x;
            int pos_y = entry.getValue().y;
            //recorremos las instalaciones ya colocadas y con letra distinta a la actual
            for(int i = 0; i < this.layout.length; i++){
                for(int j = 0; j < this.layout[0].length; j++)
                    if(this.layout[i][j] != ' ' && this.layout[i][j] != letra && pos_valida(i, j, n_columnas, letra_pos.size())){
                        //frecuencia entre la letra actual y la letra en la instalación actual
                        double frecuencia = Mat_traf[letra_pos.get(letra)][letra_pos.get(this.layout[i][j])];
                        // distancia entre la letra actual y la letra en la instalación actual
                        double distancia = Mat_dist[(pos_x * this.layout[0].length) + pos_y][(i * this.layout[0].length) + j];
                        termino_1 += frecuencia * distancia;
                    }
            }
        }
        return termino_1;
    }

    /**
     * Retorna el càlcul de la matriu C1 de la disposició actual
     * @param m És el nombre de lletres del abecedari encara no utilitzades
     * @param pos_libres vector que guarda les posicions lliures a la disposició actual
     * @param letras_libres Vector que guarda les lletres del abecedari encara no utilitzades a la disposició actual
     * @param letra_pos Mapa amb cada lletra amb la seva posició a les matrius de trànsit i distància
     * @param n_columnas el nombre de columnes del teclat
     * @param Mat_dist Matriu de distàncies per cada parell de posicions al teclat
     * @param Mat_traf Matriu de freqüències per cada parell de lletres al abecedari
     * @return La matriu C1 en una matriu de doubles
     */

    public double[][] calculo_C1(int m, ArrayList<pos> pos_libres, ArrayList<Character> letras_libres, Map<Character, Integer> letra_pos, int n_columnas, double[][] Mat_dist, double[][] Mat_traf){
        double[][] Mat_c1 = new double[m][m];

        int letra_num, posicion;
        letra_num = posicion = 0;
        for(Map.Entry<Character, Integer> entry : letra_pos.entrySet() ){
            char letra_actual = entry.getKey();
            if(!this.letres_usades.containsKey(letra_actual)){
                letras_libres.add(letra_actual);
                for(int i = 0; i < this.layout.length; i++){
                    for(int j = 0; j < this.layout[0].length; j++) {
                        if (this.layout[i][j] == ' ' && pos_valida(i, j, n_columnas, letra_pos.size())) {
                            if(pos_libres.size() != m){
                                pos p = new pos(i, j);
                                pos_libres.add(p);
                            }
                            double suma = 0.0;
                            for (Map.Entry<Character, pos> entry_2 : this.letres_usades.entrySet()) {
                                char letra = entry_2.getKey();
                                int pos_x = entry_2.getValue().x;
                                int pos_y = entry_2.getValue().y;
                                //frecuencia entre la letra actual y la letra usada en la que estamos
                                double frecuencia = Mat_traf[letra_pos.get(letra_actual)][letra_pos.get(letra)];
                                double frecuencia2 = Mat_traf[letra_pos.get(letra)][letra_pos.get(letra_actual)];
                                // distancia entre la posicion de la letra actual y la letra usada en su instalación
                                double distancia = Mat_dist[(i * this.layout[0].length) + j][(pos_x * this.layout[0].length) + pos_y];
                                suma += frecuencia * distancia;
                                suma += frecuencia2 *distancia;
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
        return Mat_c1;
    }

    /**
     * Retorna el càlcul de la matriu C2 de la disposició actual
     * @param m És el nombre de lletres del abecedari encara no utilitzades
     * @param pos_libres vector amb les posicions lliures a la disposició actual
     * @param letras_libres Vector amb les lletres del abecedari encara no utilitzades a la disposició actual
     * @param letra_pos Mapa amb cada lletra amb la seva posició a les matrius de trànsit i distància
     * @param Mat_dist Matriu de distàncies per cada parell de posicions al teclat
     * @param Mat_traf Matriu de freqüències per cada parell de lletres al abecedari
     * @return La matriu C2 en una matriu de doubles
     */

    public double[][] calculo_C2(int m, ArrayList<pos> pos_libres, ArrayList<Character> letras_libres, Map<Character, Integer> letra_pos, double[][] Mat_dist, double[][] Mat_traf){
        double[][] Mat_c2 = new double[m][m];

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
                        D.add(Mat_dist[(ubicacion_actual.x * this.layout[0].length) + ubicacion_actual.y][(ubicacion_resto.x * this.layout[0].length) + ubicacion_resto.y]);
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

        return Mat_c2;
    }

    /**
     * Retorna el càlcul del terme 2 de la disposició actual
     * @param m És el nombre de lletres del abecedari encara no utilitzades
     * @param letra_pos Mapa amb cada lletra amb la seva posició a les matrius de trànsit i distància
     * @param n_columnas el nombre de columnes del teclat
     * @param Mat_dist Matriu de distàncies per cada parell de posicions al teclat
     * @param Mat_traf Matriu de freqüències per cada parell de lletres al abecedari
     * @return El terme 2 en un double, la assignació lineal óptima de la matriu C1 més C2.
     */

    public double calcular_termino_2(int m, Map<Character, Integer> letra_pos, int n_columnas, double[][] Mat_dist, double[][] Mat_traf){
        double coste_termino_2 = 0.0;
        double[][] Mat_c1, Mat_c2;
        Mat_c1 = new double[m][m];
        Mat_c2 = new double[m][m];
        ArrayList<pos> pos_libres = new ArrayList<>();
        ArrayList<Character> letras_libres = new ArrayList<>();

        //Cálculo de la matriz C1

        Mat_c1 = calculo_C1(m, pos_libres, letras_libres, letra_pos, n_columnas, Mat_dist, Mat_traf);


        //Cálculo de la matriz C2

        Mat_c2 = calculo_C2(m, pos_libres, letras_libres, letra_pos, Mat_dist, Mat_traf);

        //Sumamos las 2 matrices C1 y C2

        double[][] suma = sumaMatrices(Mat_c1, Mat_c2);

        //resolvemos el coste de la asignación lineal óptima de c1 + c2 mediante algoritmo hungarian
        HungarianAlgorithm ha = new HungarianAlgorithm(suma);
        double valoroptim = ha.trobarAssignacioOptima();
        coste_termino_2 = valoroptim;
        return coste_termino_2;
    }


    /**
     * Iguala la cota del node a la suma del terme 1 més el terme 2
     * @param letra_pos Mapa amb cada lletra amb la seva posició a les matrius de trànsit i distància
     * @param n_columnas el nombre de columnes del teclat
     * @param Mat_dist Matriu de distàncies per cada parell de posicions al teclat
     * @param Mat_traf Matriu de freqüències per cada parell de lletres al abecedari
     */

    public void calcular_cota(Map<Character, Integer> letra_pos, int n_columnas, double[][] Mat_dist, double[][] Mat_traf){
        //Cálculo término 1
        double termino_1 = calcular_termino_1(letra_pos, n_columnas, Mat_dist, Mat_traf);

        //Cálculo del termino 2

        double termino_2 = 0.0;
        int m = letra_pos.size() - this.letres_usades.size(); // número de letras sin utilizar
        if(m != 0) termino_2 = calcular_termino_2(m,  letra_pos, n_columnas,Mat_dist, Mat_traf);




        this.cota =  termino_1 + termino_2;
    }
}

//Francisco Torredemer
