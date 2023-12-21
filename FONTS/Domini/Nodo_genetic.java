package Domini;

import java.util.*;


/**
 * Nodo_genetic és una classe que conté una disposició del teclat i el seu fitness(cota)
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */


public class Nodo_genetic {

    /**
     * Disposició del teclat, per cada lletra la seva posició
     */
    public Map<Character, Integer> disposicio;
    /**
     * Disposició del teclat, per cada posició la seva lletra
     */
    public Map<Integer, Character> pos_letra;

    /**
     * Fitness(cota) de la dispoció del teclat
     */
    public double fitness;

    /**
     * Modifica el layout del node a un completament aleatori
     * @param lletres Les lletres del abecedari
     */

    public void disposicio_random(Set<Character> lletres){
        List<Character> lista_random = new ArrayList<>(lletres);
        Collections.shuffle(lista_random);
        Map<Character, Integer> disposicio = new HashMap<>();
        Map<Integer, Character> pos_letra = new HashMap<>();

        for(int i = 0; i < lletres.size(); ++i){
            disposicio.put(lista_random.get(i), i);
            pos_letra.put(i, lista_random.get(i));
        }
        this.disposicio = disposicio;
        this.pos_letra = pos_letra;
    }

    /**
     * Comprova si un altre node o objecte és igual al node
     * @param obj El objecte a comparar amb el node
     * @return Si el objecte és igual al node o no
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Son la misma instancia
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // No son del mismo tipo
        }
        Nodo_genetic otroNodo = (Nodo_genetic) obj;

        return Objects.equals(disposicio, otroNodo.disposicio)
                && Objects.equals(pos_letra, otroNodo.pos_letra)
                && Objects.equals(fitness, otroNodo.fitness);
    }

    /**
     * Modifica la disposició del node, intercanvia 2 posicions
     */

    public void mutacion(){
        int tamany = disposicio.size();
        Random random = new Random();

        //obtenemos 2 posiciones aleatorias entre 0 y el numero n de letras menos 1
        int p1 = random.nextInt(tamany);
        int p2 = random.nextInt(tamany);

        //aseguramos que sean distintas
        while(p1 == p2){
            p1 = random.nextInt(tamany);
            p2 = random.nextInt(tamany);
        }

        char letra_1 = pos_letra.get(p1);
        char letra_2 = pos_letra.get(p2);

        //borramos como estaban antes
        disposicio.remove(letra_1);
        disposicio.remove(letra_2);
        pos_letra.remove(p1);
        pos_letra.remove(p2);

        //intercambiamos la posicion de las letras
        disposicio.put(letra_1, p2);
        disposicio.put(letra_2, p1);
        pos_letra.put(p2, letra_1);
        pos_letra.put(p1, letra_2);
    }

    /**
     * Creua els dos nodes pares en el layout del fill
     * @param n1 És el node pare 1
     * @param n2 És el node pare 2
     */


    public void crossover(Nodo_genetic n1, Nodo_genetic n2){
        int tamany = n1.disposicio.size();
        Random random = new Random();

        //obtenemos 2 puntos aleatorios de corte entre 0 y el numero n de letras menos 1
        int punto_corte1 = random.nextInt(tamany);
        int punto_corte2 = random.nextInt(tamany);

        while(punto_corte1 >= punto_corte2){
            punto_corte1 = random.nextInt(tamany);
            punto_corte2 = random.nextInt(tamany);
        }

        //creamos las estructuras del hijo
        Map<Character, Integer> disposicio_fill = new HashMap<>();
        Map<Integer, Character> pos_letra_fill = new HashMap<>();

        //añadimos las posiciones entre los dos puntos de corte al hijo

        for(int i = punto_corte1; i <= punto_corte2; ++i){
            char letra = n1.pos_letra.get(i);
            disposicio_fill.put(letra, i);
            pos_letra_fill.put(i, letra);
        }
        //creamos una cola para guardar las posiciones que hemos de completar en el hijo
            Queue<Integer> cola = new LinkedList<>();

        //recorremos las posiciones del punto de corte 2 hasta el final del padre n2
        for(int i = (punto_corte2 + 1); i < tamany; ++i){
            cola.add(i);
            char letra = n2.pos_letra.get(i);
            if(!disposicio_fill.containsKey(letra)){
                int x = cola.poll();
                disposicio_fill.put(letra, x);
                pos_letra_fill.put(x, letra);
            }
        }
        //recorremos las posiciones de la 0 hasta el punto de corte 2 en el padre n2
        for(int i = 0; i <= punto_corte2; ++i){
            if(i < punto_corte1) cola.add(i);
            char letra = n2.pos_letra.get(i);
            if(!disposicio_fill.containsKey(letra)){
                int x = cola.poll();
                disposicio_fill.put(letra, x);
                pos_letra_fill.put(x, letra);
            }
        }
        this.disposicio = disposicio_fill;
        this.pos_letra = pos_letra_fill;
    }

    /**
     * Calcula el fitness del node
     * @param Mat_dist La matriu de distàncies de les posicions del teclat
     * @param Mat_traf La matriu de freqüències de les lletres del teclat
     * @param letra_pos Map amb la posició de cada lletra a les matrius anteriors
     */

    public void calcular_fitness(double[][] Mat_dist, double[][] Mat_traf, Map<Character, Integer> letra_pos){
        double suma = 0.0;
        for(Map.Entry<Character, Integer> entry : this.disposicio.entrySet()){
            int posicio = entry.getValue();
            char letra = entry.getKey();

            for(Map.Entry<Character, Integer> entry2 : this.disposicio.entrySet()) {
                int posicio2 = entry2.getValue();
                char letra2 = entry2.getKey();
                if(posicio != posicio2){
                    //frecuencia entre la letra actual y la letra en la instalación actual
                    double frecuencia = Mat_traf[letra_pos.get(letra)][letra_pos.get(letra2)];
                    // distancia entre la letra actual y la letra en la instalación actual
                    double distancia = Mat_dist[posicio][posicio2];
                    suma += frecuencia * distancia;
                }
            }

        }
        this.fitness = 1.0/suma;
    }
}
