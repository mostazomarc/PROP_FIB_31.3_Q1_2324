package Excepcions;

public class LlistaBuida extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "LlistaBuida";
    }
    public LlistaBuida() {
        super("La llista de paraules entrada es buida");
    }
}
