package Excepcions;

/**
 * Excepció que es llença quan el tipus d'arxiu no es suportat
 */
public class TipusArxiuNoSuportat extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "TipusArxiuNoSuportat";
    }
    public TipusArxiuNoSuportat() {
        super("El tipus d'arxiu introduit no es suportat");
    }
    public TipusArxiuNoSuportat(String s) {
        super("El tipus d'arxiu " + s + " no es suportat");
    }
}
