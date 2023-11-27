package Excepcions;

/**
 * TeclatNoExisteix es una classe que representa una excepció que es llença quan el teclat no existeix
 * <p>TeclatNoExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
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

