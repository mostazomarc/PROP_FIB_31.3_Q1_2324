package Excepcions;

/**
 * Excepció que es llença quan el layout no és vàlid
 */
public class LayoutNoValid extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LayoutNoValid";
    }

    public LayoutNoValid(int size, int n, int m) {super("Layout No Valid: el nombre de lletres =  " + size  + " supera les " +n*m+ " posicions disponibles" );}

}
