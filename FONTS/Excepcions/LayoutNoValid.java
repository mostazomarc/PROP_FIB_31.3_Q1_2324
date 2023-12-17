package Excepcions;

/**
 * Excepció que es llença quan el layout no és vàlid
 */
public class LayoutNoValid extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LayoutNoValid";
    }

    public LayoutNoValid(int size, int n, int m) {super("Layout No Valid: el layout és massa petit, no caben les " + size  + " lletres en el teclat de " +n+ " files i "+ m +" columnes, prova amb un layout més gran" );}

}
