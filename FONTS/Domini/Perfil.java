

public class Perfil {

    private String Usuari;
    private String Contrasenya;
    private Frecuencia[] Frecuencies;


    //Crear usuari amb nom i contrasenya
    public Perfil (String User, String pswd) {
        Usuari = User;
        Contrasenya = pswd;
    }

    //Crear Usuari sense contrasenya
    public Perfil (String User) {
        Usuari = User;
    }

}