package Domini;

/**
 * pos és una classe que representa una posició al layout del teclat amb una fila x i una columna y
 *
 * @author Francisco Torredemer (francisco.torredemer@estudiantat.upc.edu)
 */

public class pos{
    /**
     * La fila de la posició
     */
    public int x;
    /**
     * La columna de la posició
     */
    public int y;

    /**
     * Creadora de la posició
     * @param pos_x La fila de la posició
     * @param pos_y La columna de la posició
     */

    public pos(int pos_x, int pos_y){
        this.x = pos_x;
        this.y = pos_y;
    }
}

//Francisco Torredemer
