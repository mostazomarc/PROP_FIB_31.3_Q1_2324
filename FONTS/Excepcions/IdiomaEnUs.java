package Excepcions;

/**
 * Excepció que es llença quan un idioma esta en us
 */
public class IdiomaEnUs extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "IdiomaEnUs";
    }
    public IdiomaEnUs() {
        super("L'Idioma està en ús");
    }
    public IdiomaEnUs(String s) {
        super("L'Idioma amb nom  " + s + " està en ús");
    }
}