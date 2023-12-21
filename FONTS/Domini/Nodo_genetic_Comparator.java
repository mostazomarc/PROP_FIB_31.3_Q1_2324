package Domini;

import java.util.Comparator;
import Domini.Nodo_genetic;

/**
 * Nodo_genetic_comparator és una classe amb el mètode per ordenar la població del algoritme genètic
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */

public class Nodo_genetic_Comparator implements Comparator<Nodo_genetic> {

    /**
     * Sobrescriu el compare entre dos nodes genètics
     * @param o1 El primer node genètic
     * @param o2 El segon node genètic
     * @return Positiu
     */
    @Override
    public int compare(Nodo_genetic o1, Nodo_genetic o2) {
        if(Double.compare(o2.fitness, o1.fitness) == 0) return 1;
        return Double.compare(o2.fitness, o1.fitness);
    }
}

//Francisco Torredemer
