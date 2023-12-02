package Excepcions;

/**
 * Excepció que es llença quan no hi ha cap perfil guardat
 */
public class CapPerfilGuardat extends ExcepcionsCreadorTeclat {
    public String getTipusExcepcio() {
        return "CapPerfilGuardat";
    }
    public CapPerfilGuardat() {
        super("No n'hi ha cap perfil guardat");
    }

}