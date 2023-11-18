package Dades;

import Domini.LlistaFrequencies;
import Domini.Idioma;
import Domini.Teclat;
import Excepcions.*;
import ControladorsDomini.CtrlDomini;

import java.io.*;
import java.util.*;

public class CtrlPersTeclats {
    private static CtrlPersTeclats singletonObject;

    //si canviesis de perfil tindires una nova llista
    private Map<String, Teclat> teclats;

    CtrlDomini controlador;



    //Pre:
    //Post: Si el teclat identificat per nomTeclat no existeix llença una excepció
    private void comprovaTeclatNoExisteix(String nomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
    }

    //Pre:
    //Post: Si el teclat identificat per nomTeclat ja existeix llença una excepció
    private void comprovaTeclatJaExisteix(String nomTeclat) throws TeclatJaExisteix {
        if (teclats.containsKey(nomTeclat)) throw new TeclatJaExisteix(nomTeclat);
    }

    //Pre:
    //Post: Retorna la instancia de CtrlFreqTeclats, si no existeix cap CtrlFreqFile es crea.
    public static CtrlPersTeclats getInstance(CtrlDomini c){
        if(singletonObject == null)
            singletonObject = new CtrlPersTeclats(c){

            };
        return singletonObject;
    }

    //Pre:
    //Post:
    private CtrlPersTeclats(CtrlDomini c) {
        teclats = new HashMap<>();
        controlador = c;
    }

    //Pre:
    //Post:
    public void carregarTeclats() throws Exception {


    }


    //Pre:
    //Post: Crea una llista de teclats per al nou perfil i guarda la del perfil anterior si n'hi ha
    public void nouPerfil(String usuari) {
    }

    //Pre:
    //Post: Guarda el conjunt de teclats del perfil actual i carrega la del perfil nou
    public void canviaPerfil(String usuari) {

    }

    //Pre:
    //Post: Es crea i s'afegeix una nova llista amb  nom: nomLlista, idioma: i i frequencies: novesEntrades
    public void afegirTeclat(Teclat t) throws ExcepcionsCreadorTeclat {
        comprovaTeclatJaExisteix(t.getNom());
        teclats.put(t.getNom(),t);
    }

    //Pre:
    //Post:
    public void eliminarTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat {
        comprovaTeclatNoExisteix(nomTeclat);
        teclats.remove(nomTeclat);
    }

    //Pre:
    //Post: Comprova que l'idioma identificat per nomIdioma no està en ús en cap teclat
    // (de moment no comprova teclats d'altres usuaris perquè fa falta capa de persistencia)
    public void comprovarUsIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat{
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
    }

    //Pre:
    //Post:
    public Teclat getTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat{
        comprovaTeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat);
    }

}


//Classe Programada per: Marc