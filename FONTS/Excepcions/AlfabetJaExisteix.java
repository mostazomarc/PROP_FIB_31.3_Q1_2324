package Excepcions;

/**
 * Excepció que es llença quan un alfabet ja existeix
 */
public class AlfabetJaExisteix extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "AlfabetJaExisteix";
    }
    public AlfabetJaExisteix() {
        super("L'Alfabet no existeix");
    }
    public AlfabetJaExisteix(String s) {
        super("L'Alfabet amb nom  " + s + " ja existeix");
    }
}
