package Domini;

import java.util.Comparator;
import Domini.Nodo_genetic;

public class Nodo_genetic_Comparator implements Comparator<Nodo_genetic> {
    @Override
    public int compare(Nodo_genetic o1, Nodo_genetic o2) {
        if(Double.compare(o2.fitness, o1.fitness) == 0) return 1;
        return Double.compare(o2.fitness, o1.fitness);
    }
}
