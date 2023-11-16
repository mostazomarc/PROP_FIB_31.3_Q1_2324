package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Perfil;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;

import java.io.*;
import java.util.*;

public class CtrlPersPerfil {
    private static CtrlPersPerfil singletonObject;

    private CtrlDomini controlador;

    private HashMap <String, Perfil> PerfilsActius;

    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlPersPerfil getInstance(CtrlDomini c){
        if(singletonObject == null)
            singletonObject = new CtrlPersPerfil(c){
            };
        return singletonObject;
    }

    private CtrlPersPerfil(CtrlDomini c) {
        PerfilsActius = new HashMap<>();
        controlador = c;
    }

    //Pre:
    //Post: s'afegeix el perfil identificat per nomPerfil
    public Perfil afegirPerfil (String nomPerfil) throws ExcepcionsCreadorTeclat {
        if (PerfilsActius.containsKey(nomPerfil)) throw new PerfilJaExisteix(nomPerfil);
        Perfil nouPerfil = new Perfil(nomPerfil,controlador);
        PerfilsActius.put(nomPerfil, nouPerfil);
        return nouPerfil;
    }

    //Pre:
    //Post: s'obté el perfi identificat per nomPerfil
    public Perfil getPerfil (String nomPerfil) throws ExcepcionsCreadorTeclat {
        if (!PerfilsActius.containsKey(nomPerfil)) throw new PerfilNoExisteix(nomPerfil);
        Perfil nouPerfil = PerfilsActius.get(nomPerfil);
        return nouPerfil;
    }

    //Pre:
    //Post: Retorna el conjunt de noms dels perfils.
    public List<String> getAllPerfils() {
        return new ArrayList<>(PerfilsActius.keySet());
    }


}

//Classe Programada per: Marc