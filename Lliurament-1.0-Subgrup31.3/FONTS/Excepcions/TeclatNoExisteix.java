package Excepcions;

public class TeclatNoExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "TeclatNoExisteix";
    }

    public TeclatNoExisteix() {
        super("El teclat no existeix");
    }

    public TeclatNoExisteix(String s) {
        super("El teclat amb id "+ s + " no existeix");
    }

}

