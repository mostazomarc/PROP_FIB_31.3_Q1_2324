package Domini;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.*;

import java.util.*;


public class Teclat {
    private String nom;
    private char[][] disposicio;
    private int dimX;
    private int dimY;
    private Idioma idioma;
    private String nomllista;

    private void comprovaLayoutValid(int size, int n, int m) throws LayoutNoValid{
        if (size > n*m) throw new LayoutNoValid(size, n, m);
    }

    //Pre:
    //Post: es crea un teclat amb nom a partir d'una llista de freqüencies i un idioma
    public Teclat(String nom, String nomll, Map<String, Integer> llistafreq, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        nomllista = nomll;
        int size = (i.getLletres()).size();
        comprovaLayoutValid(size,n,m);
        dimX = n;
        dimY = m;
        Estrategia estrategia = new BranchandBound();
        disposicio = estrategia.solve(llistafreq, i.getLletres(), dimX, dimY);
    }

    public Teclat(String nom, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        int size = (i.getLletres()).size();
        comprovaLayoutValid(size,n,m);
        dimX = n;
        dimY = m;
        Estrategia estrategia = new BranchandBound();
        nomllista = i.getLlistaFreq().getNom();
        disposicio = estrategia.solve(i.getFrequencies(), i.getLletres(), dimX, dimY);

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
    public char[][] getDisposicio() {
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