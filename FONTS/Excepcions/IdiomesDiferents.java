package Excepcions;

public class IdiomesDiferents extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "IdiomesDiferents";
    }

    public IdiomesDiferents(String nomidiomallista, String nomidiomateclat) {super("Idiomes diferents: l'idioma de la llista:  " + nomidiomallista  + " es diferent a l'idioma del teclat que es vol crear: " + nomidiomateclat );}
}