package Domini;

import Domini.*;
import Stubs.*;

import java.util.*;


public class Teclat {
    private String nom;
    private char[][] disposicio;
    private int dimX;
    private int dimY;
    private Idioma idioma;
    private String nomllista;

    //Pre:
    //Post: es crea un teclat amb nom a partir d'una llista de freqüencies i un idioma
    public Teclat(String nom, String nomll, Map<String, Integer> llistafreq, Idioma i) {
        this.nom = nom;
        idioma = i;
        nomllista = nomll;
        Estrategia estrategia = new BranchandBound();
        disposicio = estrategia.solve(llistafreq, i.getLletres(), 3, 10);

    }

    public Teclat(String nom, Idioma i) {
        this.nom = nom;
        idioma = i;
        Estrategia estrategia = new BranchandBound();
        nomllista = i.getLlistaFreq().getNom();
        disposicio = estrategia.solve(i.getFrequencies(), i.getLletres(), 3, 10);

    }

    //Pre:
    //Post:
    public String getNomLlistaFreq() {return nomllista; }

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

    //Pre:
    //Post: es canvia la disposició del teclat
    public char[][] canviaDisposicioTeclat() {

        return null;
    }

    public String getNomIdiomaTeclat() {
        return idioma.getNom();
    }
}