package Excepcions;

public class AlfabetEnUs extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "AlfabetEnUs";
    }
    public AlfabetEnUs() {
        super("L'Alfabet està en ús");
    }
    public AlfabetEnUs(String s) {
        super("L'Alfabet amb nom  " + s + " està en ús");
    }
}
