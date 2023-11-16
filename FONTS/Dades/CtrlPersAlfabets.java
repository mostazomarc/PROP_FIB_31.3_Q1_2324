package Dades;

import Domini.Alfabet;
import Excepcions.AlfabetJaExisteix;
import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;

import java.util.*;

public class CtrlPersAlfabets {
    private static CtrlPersAlfabets singletonObject;
    private TreeMap<String, Alfabet> Alfabets;

    //Pre:
    //Post: Retorna la instancia de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
    public static CtrlPersAlfabets getInstance(){
        if(singletonObject == null)
            singletonObject = new CtrlPersAlfabets(){

            };
        return singletonObject;
    }
    private CtrlPersAlfabets() {
        Alfabets = new TreeMap<String, Alfabet>();;
    }

    //Pre:
    //Post: s'afegeix l'Alfabet identificat per filename.length() - 4
    public void afegirAlfabet(String filename, List<String> LlistaLlegida) throws ExcepcionsCreadorTeclat {

        String nomAlfabet = filename.substring(0, filename.length() - 4);

        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) throw new AlfabetJaExisteix(nomAlfabet);

        Set<Character> lletres = new HashSet<Character>();

        for (String linia : LlistaLlegida) {
            for (char lletra : linia.toCharArray()) {
                lletres.add(lletra);
            }
        }

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, lletres);
        Alfabets.put(nomAlfabet.toLowerCase(), nouAlfabet);
    }

    //Pre:
    //Post: Retorna TRUE si existeix un Alfabet amb nomAlfabet, FALSE en cas contrari
    public boolean existeix(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) return true;
        return false;
    }

    //Pre:
    //Post: Retorna l'Alfabet identificat per nomAlfabet
    public Alfabet getAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        if (!existeix(nomAlfabet)) throw new AlfabetNoExisteix(nomAlfabet);
        return Alfabets.get(nomAlfabet);
    }

    //Pre:
    //Post: Retorna el conjunt d'Alfabets
    public TreeMap<String, Alfabet> getAlfabets() {
        return Alfabets;
    }

}

//Classe Programada per: Arnau Tajahuerce
