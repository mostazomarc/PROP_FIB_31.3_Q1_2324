package Excepcions;
/**
 * LlistaBuida es una classe que representa una excepció que es llença quan la llista de paraules entrada es buida
 * <p>LlistaBuida exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class LlistaBuida extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LlistaBuida";
    }
    public LlistaBuida() {
        super("La llista de paraules entrada es buida");
    }
}
