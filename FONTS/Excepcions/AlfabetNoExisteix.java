package Excepcions;

public class AlfabetNoExisteix extends ExcepcionsIdiomesAlfabets {
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
