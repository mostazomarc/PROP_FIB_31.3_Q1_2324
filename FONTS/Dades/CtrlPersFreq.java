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

    //Pre:
    //Post: Si la llista idetificada per nomLlista no existeix llença una excepció
    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    //Pre:
    //Post: Si la llista idetificada per nomLlista ja existeix llença una excepció
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

    //Pre:
    //Post: Crea un conjunt de llistes de frequencies i guarda el controlador
    private CtrlPersFreq(CtrlDomini c) {
        frequencies = new HashMap<>();
        controlador = c;
    }

    //Pre:
    //Post: Carrèga les llistes de freqüencia dels arxius on estàn guardades (de moment crea noves, funcionarà al tenir capa de persistencia)
    public void carregarFrequencies() throws Exception {
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

    //Pre:
    //Post: S'afegeix una nova llista de frequencies amb nomLlista i idioma
    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i) {
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista,i);
        frequencies.put(llista.getNom(),llista);
        return llista;
    }

    //Pre:
    //Post: Es crea i s'afegeix una nova llista amb  nom: nomLlista, idioma: i i frequencies: novesEntrades
    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i, Map<String, Integer> novesEntrades) {
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista,i,novesEntrades);
        frequencies.put(llista.getNom(),llista);
        return llista;
    }

    //Pre:
    //Post: S'elimina la llista identificada per nomLlista
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

    //Pre:
    //Post: S'obtè la llista identificada per nomLlista
    public LlistaFrequencies getLlistaFreq(String nomLlista) {
        return frequencies.get(nomLlista);
    }

}


//Classe Programada per: Marc