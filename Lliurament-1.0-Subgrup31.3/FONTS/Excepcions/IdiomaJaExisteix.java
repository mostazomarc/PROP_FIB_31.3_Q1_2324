package Excepcions;

public class IdiomaJaExisteix extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "IdiomaJaExisteix";
    }
    public IdiomaJaExisteix() {
        super("L'Idioma ja existeix");
    }
    public IdiomaJaExisteix(String s) {
        super("L'Idioma amb nom  " + s + " ja existeix");
    }
}
