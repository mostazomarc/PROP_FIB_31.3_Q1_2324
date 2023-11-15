package Excepcions;

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