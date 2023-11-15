package Excepcions;

public abstract class ExcepcionsIdiomesAlfabets extends Exception {
    public abstract String getTipusExcepcio();
    public ExcepcionsIdiomesAlfabets() {
        super();
    }
    public ExcepcionsIdiomesAlfabets(String s) {
        super(s);
    }
}
