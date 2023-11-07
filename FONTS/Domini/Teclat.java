package Domini;

import Domini.*;
import Stubs.*;

import java.util.*;


public class Teclat {
    private String nom;
    private char[][] disposicio;
    private int dimX;
    private int dimY;
    IdiomaStub idioma;

    private CreadoraTeclatStub creadora;

    //Pre:
    //Post: es crea un teclat amb nom a partir d'una llista de freqüencies i un idioma
    public Teclat(String nom, LlistaFrequencies freq, IdiomaStub i) {
        this.nom = nom;
        idioma = i;
        disposicio = creadora.crearTeclat(freq,i.getLletres());
    }


    //Pre:
    //Post: es retorna el nom del teclat
    public String getNomTeclat() {
        return nom;
    }

    //Pre:
    //Post: es retorna la disposició de teclat
    public char[][] getTeclat() {
        return disposicio;
    }

}