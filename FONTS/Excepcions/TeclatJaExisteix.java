package Excepcions;

public class TeclatJaExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "PerfilNoExisteix";
    }

    public TeclatJaExisteix() {
        super("El teclat ja existeix");
    }

    public TeclatJaExisteix(String s) {
        super("El teclat amb id "+ s + " ja existeix");
    }

}

//Classe Programada per: Marc