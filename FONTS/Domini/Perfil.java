package Domini;

public class Perfil {

    private String Usuari;
    private String Contrasenya;
    //private Frequencia[] Frequencies;

    public Perfil (String User, String pswd) {
        Usuari = User;
        Contrasenya = pswd;
    }

    public Perfil (String User) {
        Usuari = User;
    }

    public String getUsuari() {
        return Usuari;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void canviaUsuari (String newUs) {
        Usuari = newUs;
    }

    public void canviaContrasenya (String newCon) {
        Contrasenya = newCon;
    }
}