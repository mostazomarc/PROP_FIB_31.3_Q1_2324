package Dades;

import Domini.LlistaFrequencies;
import Domini.Perfil;
import Excepcions.*;

import java.io.*;
import java.util.*;

public class CtrlPersFreq {
    private static CtrlPersFreq singletonObject;

    private Map<String, Map<String,LlistaFrequencies>>  frequencies; //Mapa <Usuari,totes les llistes de frequencies del usuari<NomLlista,llista>>

    private Map<String,LlistaFrequencies> llistesPerfilActual;

    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!llistesPerfilActual.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (llistesPerfilActual.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
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


    public void nouPerfil(String usuari) {
        frequencies.put(usuari, new HashMap<>());
        llistesPerfilActual = frequencies.get(usuari);
    }

    public void canviaPerfil(String usuari) {
        llistesPerfilActual = frequencies.get(usuari);
    }

    public void afegirLlistaFreq(LlistaFrequencies llista) {
        llistesPerfilActual.put(llista.getNom(),llista);
    }


    public LlistaFrequencies getLlistaFreq(String nomLlista) {
        return llistesPerfilActual.get(nomLlista);
    }

}


//Classe Programada per: Marc