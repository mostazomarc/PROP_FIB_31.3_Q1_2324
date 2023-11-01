package ControladorsDomini;

import java.util.*;
import java.io.*;
import Domini.*;
import Dades.*;

public class CtrlDomini {
    private Perfil PerfilActual; //Perfil que esta usant actualment el programa
    private String Estrategia; //Estrategia utilitzada en la fabricació del teclat
    private HashMap <String, Perfil> PerfilsActius; //Conjunt d'usuaris registrats
    private static CtrlDomini singletonObject;
    private CtrlFreqFile ctrlFreqFile;


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
        ctrlFreqFile = CtrlFreqFile.getInstance();
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

    public void llegirAriuFreq(String filename) {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaFrequencies = ctrlFreqFile.llegirArxiuFreq(filename);

        for (String linia : LlistaFrequencies) {
            String[] parella = linia.split(" ");
            System.out.println(parella[0] + " " + parella[1]);
        }
    }

    public void llegirLlistaFreq(String tipusArxiu, String filename) {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiuFreq(filename);
        Map<String, Integer> novesEntrades = new HashMap<>();


        if (tipusArxiu == "llista") {
            for (String linia : LlistaLlegida) {
                String[] parella = linia.split(" ");
                String paraula = parella[0];
                Integer frequencia = Integer.parseInt(parella[1]);

                if (novesEntrades.containsKey(paraula)) {
                    // Si la paraula ja existeix obtenir frequencia actual
                    int FrecVella = novesEntrades.get(paraula);
                    // Sumar la nova vella frequencia a la nova
                    frequencia += FrecVella;
                }

                novesEntrades.put(paraula,frequencia);
            }
        } else {}

        PerfilActual.afegirLlistaFreq(filename,novesEntrades);

    }

    public Set<String> getNomLlistesGuardades() {
        return PerfilActual.getNomAllLlistes();
    }



}