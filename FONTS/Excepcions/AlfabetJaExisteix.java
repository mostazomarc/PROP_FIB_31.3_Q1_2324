package Excepcions;

public class AlfabetJaExisteix extends ExcepcionsIdiomesAlfabets {
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
