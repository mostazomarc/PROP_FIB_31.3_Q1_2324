package Excepcions;

/**
 * Excepció que es llença quan un idioma no existeix
 */
public class IdiomaNoExisteix extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "IdiomaNoExisteix";
    }
    public IdiomaNoExisteix() {
        super("L'Idioma no existeix");
    }
    public IdiomaNoExisteix(String s) {
        super("L'Idioma amb nom  " + s + " no existeix");
    }
}
