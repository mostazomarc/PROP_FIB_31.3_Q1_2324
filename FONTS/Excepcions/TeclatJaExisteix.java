package Excepcions;

/**
 * TeclatJaExisteix es una classe que representa una excepció que es llença quan el teclat ja existeix
 * <p>TeclatJaExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class TeclatJaExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "TeclatNoExisteix";
    }

    public TeclatJaExisteix() {
        super("El teclat ja existeix");
    }

    public TeclatJaExisteix(String s) {
        super("El teclat amb id "+ s + " ja existeix");
    }

}

