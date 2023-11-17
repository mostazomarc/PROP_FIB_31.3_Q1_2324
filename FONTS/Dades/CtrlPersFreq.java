package Dades;

import Domini.LlistaFrequencies;
import Domini.Idioma;
import Excepcions.*;
import ControladorsDomini.CtrlDomini;

import java.io.*;
import java.util.*;

public class CtrlPersFreq {
    private static CtrlPersFreq singletonObject;

    //si canviesis de perfil tindires una nova llista
    private Map<String,LlistaFrequencies> frequencies;

    private CtrlDomini controlador;

    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }

    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlPersFreq getInstance(CtrlDomini c){
        if(singletonObject == null)
            singletonObject = new CtrlPersFreq(c){

            };
        return singletonObject;
    }

    private CtrlPersFreq(CtrlDomini c) {
        frequencies = new HashMap<>();
        controlador = c;
    }

    public void carregarFrequencies() throws ExcepcionsCreadorTeclat {
        Map<String, Integer> novesEntrades = new HashMap<>();
        controlador.novaLlistaPerfil("llista","catalaFreq.txt", "Català", novesEntrades);
    }


    //Pre:
    //Post: Crea una llista de llistes per al nou perfil i guarda la del perfil anterior si n'hi ha
    public void nouPerfil(String usuari) {
    }

    //Pre:
    //Post: Guarda la llista de llites del perfil actual i carrega la del perfil nou
    public void canviaPerfil(String usuari) {

    }

    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i) {
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista,i);
        frequencies.put(llista.getNom(),llista);
        return llista;
    }

    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i, Map<String, Integer> novesEntrades) {
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista,i,novesEntrades);
        frequencies.put(llista.getNom(),llista);
        return llista;
    }

    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(nomLlista);
        frequencies.remove(nomLlista);
    }

    //Pre:
    //Post: Comprova que l'idioma identificat per nomIdioma no està en ús en cap llista de frequencies
    // (de moment no comprova llistes de frequencies d'altres usuaris perquè fa falta capa de persistencia)
    public void comprovarUsIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat{
        for (Map.Entry<String, LlistaFrequencies> llista : frequencies.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
    }



    public LlistaFrequencies getLlistaFreq(String nomLlista) {
        return frequencies.get(nomLlista);
    }

}


//Classe Programada per: Marc