package Domini;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.*;

import java.util.*;


public class Teclat {
    private String nom;
    private int dimX;
    private int dimY;
    private char[][] disposicio;
    private Idioma idioma;
    private LlistaFrequencies llistafreq;

    private void comprovaLayoutValid(int n, int m) throws LayoutNoValid{
        if (idioma.getLletres().size() > n*m) throw new LayoutNoValid(idioma.getLletres().size(), n, m);
    }

    private void comprovaIdiomes(String nomidiomallista, String nomidiomateclat) throws IdiomesDiferents {
        if (nomidiomallista != nomidiomateclat) throw new IdiomesDiferents(nomidiomallista, nomidiomateclat);
    }

    //Pre: la llista de freqüències és una llista del Perfil que l'ha creat
    //Post: es crea un teclat amb nom a partir d'una llista de freqüencies i un idioma
    public Teclat(String nom, LlistaFrequencies llistafreq, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        comprovaIdiomes(llistafreq.getNomIdioma(),i.getNom());
        this.nom = nom;
        idioma = i;
        this.llistafreq = llistafreq;
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        Estrategia estrategia = new BranchandBound();
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), dimX, dimY);
    }

    public Teclat(String nom, Idioma i, int n, int m) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        this.llistafreq = i.getLlistaFreq();
        Estrategia estrategia = new BranchandBound();
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), i.getLletres(), dimX, dimY);

    }

    //Pre:
    //Post:
    public String getNomLlistaFreq() {return llistafreq.getNom(); }


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

    public String getNomIdioma() {
        return idioma.getNom();
    }

    public Integer getDimX() {return dimX; }

    public Integer getDimY() {return dimY; }

    public void modificarLayout(int n, int m) throws ExcepcionsCreadorTeclat {
        comprovaLayoutValid(n, m);
        Estrategia estrategia = new BranchandBound();
        dimX = n;
        dimY = m;
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), n, m);
    }
}