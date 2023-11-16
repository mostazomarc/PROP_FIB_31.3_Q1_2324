package Dades;

import Domini.LlistaFrequencies;
import Domini.Idioma;
import Excepcions.*;

import java.io.*;
import java.util.*;

public class CtrlPersFreq {
    private static CtrlPersFreq singletonObject;

    //si canviesis de perfil tindires una nova llista
    private Map<String,LlistaFrequencies> frequencies;

    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }

    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlPersFreq getInstance(){
        if(singletonObject == null)
            singletonObject = new CtrlPersFreq(){

            };
        return singletonObject;
    }

    private CtrlPersFreq() {
        frequencies = new HashMap<>();
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



    public LlistaFrequencies getLlistaFreq(String nomLlista) {
        return frequencies.get(nomLlista);
    }

}


//Classe Programada per: Marc