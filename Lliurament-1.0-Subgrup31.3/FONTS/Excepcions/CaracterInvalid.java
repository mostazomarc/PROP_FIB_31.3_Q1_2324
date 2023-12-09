package Excepcions;

public class CaracterInvalid extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "CaracterInvalid";
    }
    public CaracterInvalid() {
        super("S'ha trobat un caràcter invàlid");
    }
    public CaracterInvalid(String s) {
        super("S'ha trobat un caràcter invàlid a l'arxiu " + s);
    }
}