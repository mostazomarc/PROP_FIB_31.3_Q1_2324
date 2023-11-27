package Excepcions;

/**
 * PerfilJaExisteix es una classe que representa una excepció que es llença quan el perfil ja existeix
 * <p>PerfilJaExisteix exten la classe ExcepcionsCreadorTeclat</p>
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class PerfilJaExisteix extends ExcepcionsCreadorTeclat{

    public String getTipusExcepcio() {
        return "PerfilJaExisteix";
    }

    public PerfilJaExisteix() {
        super("El perfil ja existeix");
    }

    public PerfilJaExisteix(String s) {
        super("El perfil amb id "+ s + " ja existeix");
    }

}

//Classe Programada per: Marc