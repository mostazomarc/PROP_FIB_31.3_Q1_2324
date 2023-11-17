package Domini;

import java.util.HashMap;
import java.util.Map;
import Domini.pos;

public class Nodo {

    public char[][] layout;
    public double cota;

    public Map<Character, pos> letres_usades;

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
