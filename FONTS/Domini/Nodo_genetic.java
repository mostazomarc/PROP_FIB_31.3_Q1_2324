package Domini;

import java.util.*;


public class Nodo_genetic {

    public Map<Character, Integer> disposicio;
    public Map<Integer, Character> pos_letra;
    public double fitness;

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
