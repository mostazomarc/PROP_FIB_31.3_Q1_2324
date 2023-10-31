package ControladorsDomini;

import java.util.HashMap;
import java.util.Map;
import Domini.*;

public class CtrlDomini {
    private Perfil PerfilActual; //Perfil que esta usant actualment el programa
    private String Estrategia; //Estrategia utilitzada en la fabricació del teclat
    private HashMap <String, Perfil> PerfilsActius; //Conjunt d'usuaris registrats
    private static CtrlDomini singletonObject;


    //Pre:
    //Post: Es crea una instancia de domini.
    public CtrlDomini(){
        inicialitzar();
    }

    //Pre:
    //Post: Retorna la instancia de Controlador Domini. Si no existeix cap instancia de CtrlDomini, es crea.
    public static CtrlDomini getInstance(){ //CtrlDomini es singleton
        if(singletonObject == null){
            singletonObject = new CtrlDomini();
        }
        return singletonObject;
    }

    //Pre:
    //Post: S'inicialitzen les variables necessaries.
    public void inicialitzar() {
        Estrategia = "QAP"; //estrategia per defecte
        PerfilsActius = new HashMap<String, Perfil>();
    }

    //Pre: Es rep un nom d'usuari
    //Post: Es crea l'usuari amb el nom rebut
    public void iniciaInstancia(String nom) {
        System.out.println("inicia sessio: " + nom +"\n");
        PerfilActual = new Perfil(nom);
        PerfilsActius.put(nom,PerfilActual);
    }


    //Pre:
    //Post: Retorna el conjunt de perfils.
    public HashMap<String, Perfil> getAllPerfils() {
        return PerfilsActius;
    }

    //Pre:
    //Post: Retorna la instancia del Perfil.
    public Perfil getPerfil(String id) {
        if (PerfilsActius.containsKey(id)) return PerfilsActius.get(id);
        else return null; //perfil no existeix
    }

    //Pre:
    //Post: Retorna la instancia del Perfil Actual.
    public String getPerfilActual() {
        return "Marc";
    }

    //Pre:
    //Post: Retorna el nom de l'estrategia per fer la distribució de teclat
    public String getEstrategiaActual() {
        return Estrategia;
    }

}