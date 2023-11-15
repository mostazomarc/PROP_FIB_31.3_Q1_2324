package Excepcions;

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