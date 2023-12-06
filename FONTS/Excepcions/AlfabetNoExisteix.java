package Excepcions;

/**
 * Excepció que es llença quan un alfabet no existeix
 */
public class AlfabetNoExisteix extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "AlfabetNoExisteix";
    }
    public AlfabetNoExisteix() {
        super("L'Alfabet no existeix");
    }
    public AlfabetNoExisteix(String s) {
        super("L'Alfabet amb nom  " + s + " no existeix");
    }
}
