package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Alfabet;
import Excepcions.AlfabetEnUs;
import Excepcions.AlfabetJaExisteix;
import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;

import java.util.*;

public class CtrlPersAlfabets {
    private static CtrlPersAlfabets singletonObject;
    private TreeMap<String, Alfabet> Alfabets;
    private CtrlDomini controlador;

    //Pre:
    //Post: Retorna la instancia de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
    public static CtrlPersAlfabets getInstance(CtrlDomini c) {
        if(singletonObject == null)
            singletonObject = new CtrlPersAlfabets(c);
        return singletonObject;
    }
    private CtrlPersAlfabets(CtrlDomini c) {
        Alfabets = new TreeMap<String, Alfabet>();
        controlador = c;
    }

    public void carregarAlfabets() throws ExcepcionsCreadorTeclat {
        controlador.afegirAlfabet("Llatí.txt");
    }

    //Pre:
    //Post: S'afegeix l'Alfabet identificat per filename.length() - 4
    public void afegirAlfabet(String filename, List<String> LlistaLlegida) throws ExcepcionsCreadorTeclat {

        String nomAlfabet = filename.substring(0, filename.length() - 4);

        if (existeix(nomAlfabet)) throw new AlfabetJaExisteix(nomAlfabet);

        Set<Character> lletres = new HashSet<Character>();

        for (String linia : LlistaLlegida) {
            for (char lletra : linia.toCharArray()) {
                lletres.add(lletra);
            }
        }

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, lletres);
        Alfabets.put(nomAlfabet.toLowerCase(), nouAlfabet);
    }

    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        Alfabet a = getAlfabet(nomAlfabet);
        if (a.numIdiomes() != 0) throw new AlfabetEnUs(nomAlfabet);
        Alfabets.remove(nomAlfabet.toLowerCase());
    }

    //Pre:
    //Post: Retorna TRUE si existeix un Alfabet amb nomAlfabet, FALSE en cas contrari
    public boolean existeix(String nomAlfabet) {
        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) return true;
        return false;
    }

    //Pre:
    //Post: Retorna l'Alfabet identificat per nomAlfabet
    public Alfabet getAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        if (!existeix(nomAlfabet)) throw new AlfabetNoExisteix();
        return Alfabets.get(nomAlfabet.toLowerCase());
    }

    //Pre:
    //Post: Retorna el conjunt d'Alfabets
    public TreeMap<String, Alfabet> getAlfabets() {
        return Alfabets;
    }

}

//Classe Programada per: Arnau Tajahuerce
