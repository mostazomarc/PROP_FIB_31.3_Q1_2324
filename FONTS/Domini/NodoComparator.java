package Domini;

import java.util.Comparator;
import Domini.Nodo;

public class NodoComparator implements Comparator<Nodo> {
    @Override
    public int compare(Nodo o1, Nodo o2) {
        return Double.compare(o1.cota, o2.cota);
    }
}


//Francisco Torredemer