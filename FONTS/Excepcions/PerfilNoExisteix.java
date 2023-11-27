package Excepcions;

/**
 * PerfilNoExisteix es una classe que representa una excepció que es llença quan el perfil no existeix
 * <p>PerfilNoExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class PerfilNoExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "PerfilNoExisteix";
    }

    public PerfilNoExisteix() {
        super("El perfil no existeix");
    }

    public PerfilNoExisteix(String s) {
        super("El perfil amb id "+ s + " no existeix");
    }

}

//Classe Programada per: Marc