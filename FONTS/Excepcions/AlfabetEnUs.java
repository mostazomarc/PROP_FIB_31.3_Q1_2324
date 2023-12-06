package Excepcions;

/**
 * Excepció que es llença quan un alfabet està en ús per algun idioma
 */
public class AlfabetEnUs extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "AlfabetEnUs";
    }
    public AlfabetEnUs() {
        super("L'Alfabet està en ús");
    }
    public AlfabetEnUs(String s) {
        super("L'Alfabet amb nom  " + s + " està en ús");
    }
}
