package Excepcions;

public class LayoutNoValid extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LayoutNoValid";
    }

    public LayoutNoValid() {super("El teclat ja existeix");}

    public LayoutNoValid(String s) {
        super("El teclat amb id "+ s + " ja existeix");
    }

}
