package Domini;

import java.util.*;

import Domini.Nodo_genetic;
public class Genetic extends Estrategia{
    // Atributos
    private double[][] Mat_dist; //Matriz de distancia entre las ubicaciones
    private double[][] Mat_traf;  //Matriz de tráfico entre las instalaciones

    private char[][] best_sol;  //Layout resultante del algoritmo

    TreeSet<Nodo_genetic> poblacion;



    //Métodos

    public Nodo_genetic seleccion_ruleta(){
        //sumamos el fitness total
        double suma = 0.0;
        for (Nodo_genetic nodo : poblacion) {
            suma += nodo.fitness;
        }

        //generamos un numero aleatorio de 0 a suma

        Random random = new Random();

        // Genera un número aleatorio en el rango [0, n)
        double numeroAleatorio = random.nextDouble() * suma;

        //el fitness acumulado hasta el nodo actual
        double suma_nodos = 0.0;

        //retornaremos el nodo cuya suma iguale o supere el numeroaleatorio
        for (Nodo_genetic nodo : poblacion) {
            suma_nodos += nodo.fitness;
            if(suma_nodos >= numeroAleatorio) return nodo;
        }

        //no llegará a aqui
        return null;
    }

    public static boolean probabilidad(double prob) {
        Random random = new Random();
        double numeroAleatorio = random.nextDouble();
        return numeroAleatorio < prob;
    }

    public char[][] algoritme_genetic(Set<Character> lletres, Map<Character, Integer> letra_pos, int n_filas, int n_columnas){

        int n = lletres.size();
        int nombre_poblacio_inicial = 4*n;
        int generacions = 100;

         poblacion = new TreeSet<>(new Nodo_genetic_Comparator());

        //generamos una población inicial aleatoria
        for(int i = 0; i < nombre_poblacio_inicial; ++i){
            Nodo_genetic node_inicial = new Nodo_genetic();
            node_inicial.disposicio_random(lletres);
            node_inicial.calcular_fitness(Mat_dist, Mat_traf, letra_pos);
            poblacion.add(node_inicial);
        }


        //vamos recorriendo generaciones
        for(int i = 0; i < generacions; ++i){

            //Reaizamos el cruce y sustuimos los mejores desciendentes por los peores de la poblacion
            int num_descendientes = nombre_poblacio_inicial/4;
            TreeSet<Nodo_genetic> auxiliar = new TreeSet<>(new Nodo_genetic_Comparator());
            for(int j = 0; j < num_descendientes; ++j){
                Nodo_genetic n1 = seleccion_ruleta();
                Nodo_genetic n2 = seleccion_ruleta();
                while(n1.equals(n2)){
                    n1 = seleccion_ruleta();
                    n2 = seleccion_ruleta();
                }
                Nodo_genetic hijo1 = new Nodo_genetic();
                hijo1.crossover(n1, n2);
                hijo1.calcular_fitness(Mat_dist, Mat_traf, letra_pos);

                Nodo_genetic hijo2 = new Nodo_genetic();
                hijo2.crossover(n2, n1);
                hijo2.calcular_fitness(Mat_dist, Mat_traf, letra_pos);

                auxiliar.add(hijo1);
                auxiliar.add(hijo2);
            }

            Iterator<Nodo_genetic> iterator = poblacion.descendingIterator();
            int cont = 0;
            while(iterator.hasNext() && cont < num_descendientes){
                iterator.next();
                iterator.remove();
                cont++;
            }

            Iterator<Nodo_genetic> iterator2 = auxiliar.iterator();
            for(int j = 0; j < num_descendientes; ++j){
                Nodo_genetic node = iterator2.next();
                poblacion.add(node);
            }



            //Realizamos la mutacion, para todos excepto para el mejor nodo, con una cierta probabilidad del 10%

            Iterator<Nodo_genetic> iterator1 = poblacion.iterator();
            TreeSet<Nodo_genetic> auxiliar2 = new TreeSet<>(new Nodo_genetic_Comparator());
            //saltamos el mejor nodo
            iterator1.next();
            while(iterator1.hasNext()){
                Nodo_genetic nodo = iterator1.next();
                if(probabilidad(0.1)){
                    iterator1.remove();
                    nodo.mutacion();
                    nodo.calcular_fitness(Mat_dist, Mat_traf, letra_pos);
                    auxiliar2.add(nodo);
                }
            }
            poblacion.addAll(auxiliar2);

        }

        //la mejor solución a la que hemos llegado
        char[][] solucio = new char[n_filas][n_columnas];
        Nodo_genetic mejor_nodo = poblacion.first();

        for(Map.Entry<Character, Integer> entry : mejor_nodo.disposicio.entrySet()){
            int posicio = entry.getValue();
            char letra = entry.getKey();
            int fila = posicio /n_columnas;
            int columna = posicio % n_columnas;

            solucio[fila][columna] = letra;
        }
        return solucio;
    }

    @Override
    public char[][] calculaDisposicio(Map<String, Integer> palabrasFrec, Set<Character> lletres, int n_filas, int n_columnas) {

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

        this.best_sol = algoritme_genetic(lletres, letra_pos, n_filas, n_columnas);


        return this.best_sol;
    }
}
