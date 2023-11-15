package Excepcions;

public class IdiomaNoExisteix extends ExcepcionsIdiomesAlfabets {
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
