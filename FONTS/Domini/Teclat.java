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

    private Map<String, Integer> llistafreq;

    private void comprovaLayoutValid(int n, int m) throws LayoutNoValid{
        if (idioma.getLletres().size() > n*m) throw new LayoutNoValid(idioma.getLletres().size(), n, m);
    }

    //Pre:
    //Post: es crea un teclat amb nom a partir d'una llista de freqüencies i un idioma
    public Teclat(String nom, String nomll, Map<String, Integer> llistafreq, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        nomllista = nomll;
        this.llistafreq = llistafreq;
        int size = (i.getLletres()).size();
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        Estrategia estrategia = new BranchandBound();
        disposicio = estrategia.solve(this.llistafreq, idioma.getLletres(), dimX, dimY);
    }

    public Teclat(String nom, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        int size = (i.getLletres()).size();
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        this.llistafreq = i.getFrequencies();
        Estrategia estrategia = new BranchandBound();
        nomllista = i.getLlistaFreq().getNom();
        disposicio = estrategia.solve(this.llistafreq, i.getLletres(), dimX, dimY);

    }

    //Pre:
    //Post:
    public String getNomLlistaFreq() {return nomllista; }

    //Pre:
    //Post: es retorna el nom del teclat
    public String getNom() {
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

    public String getNomIdioma() {
        return idioma.getNom();
    }

    public void modificarLayout(int n, int m) throws ExcepcionsCreadorTeclat {
        comprovaLayoutValid(n, m);
        Estrategia estrategia = new BranchandBound();
        dimX = n;
        dimY = m;
        disposicio = estrategia.solve(this.llistafreq, idioma.getLletres(), n, m);
    }
}