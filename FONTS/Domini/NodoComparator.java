package Domini;

import java.util.Comparator;
import Domini.Nodo;

/**
 * NodoComparator és una classe amb el mètode per ordenar les solucions parcials del algoritme Branch and Bound
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */

public class NodoComparator implements Comparator<Nodo> {

    /**
     * Sobrescriu el compare entre dos nodes
     * @param o1 El primer node
     * @param o2 El segon node
     * @return Positiu
     */
    @Override
    public int compare(Nodo o1, Nodo o2) {
        return Double.compare(o1.cota, o2.cota);
    }
}


//Francisco Torredemer