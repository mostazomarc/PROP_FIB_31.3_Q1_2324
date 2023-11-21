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

    //Pre: n es el nomnbre de files i m el nombre de columnes
    //Post: si n*m < el nombre de lletres retorna l'excepció LayoutNovalid
    private void comprovaLayoutValid(int n, int m) throws LayoutNoValid{
        if (idioma.getLletres().size() > n*m) throw new LayoutNoValid(idioma.getLletres().size(), n, m);
    }

    //Pre: nomidiomallista és el nom de l'idioma de la llista i nomidiomateclat és el nom de l'idioma del teclat
    //Post: si són idiomes diferents, és a dir, nomidiomallista != nomidiomateclat, retorna l'excepció IdiomesDiferents
    private void comprovaIdiomes(String nomidiomallista, String nomidiomateclat) throws IdiomesDiferents {
        if (nomidiomallista != nomidiomateclat) throw new IdiomesDiferents(nomidiomallista, nomidiomateclat);
    }

    //Pre: la llista de freqüències llistafreq és una llista del Perfil, l'idioma i és l'idioma del teclat, nom és el nom del teclat, n és el nombre de files i m el nombre de columnes
    //Post: Es crea un teclat amb els paràmetres indicats i es comprova que siguin vàlids
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

    //Pre: l'idioma i és l'idioma del teclat, nom és el nom del teclat, n és el nombre de files i m el nombre de columnes
    //Post: Es crea un teclat amb els paràmetres indicats i es comprova que siguin vàlids. En aquest cas la llista de freqüències que s'usa és la predeterminada de l'idioma i
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
    //Post: Retorna el nom de la llista de freqüències
    public String getNomLlistaFreq() {return llistafreq.getNom(); }


    //Pre:
    //Post: Retorna el nom del teclat
    public String getNom() {
        return nom;
    }

    //Pre:
    //Post: Retorna la disposició de teclat
    public char[][] getDisposicio() {
        return disposicio;
    }

    //Pre:
    //Post: Retorna el nom de l'idioma del teclat
    public String getNomIdioma() {
        return idioma.getNom();
    }

    //Pre:
    //Post: Retorna el nombre de files del teclat
    public Integer getDimX() {return dimX; }

    //Pre:
    //Post: Retorna el nombre de columnes del teclat
    public Integer getDimY() {return dimY; }

    //Pre: n i m és el nou nombre de files i columnes respectivament
    //Post: comprova que siguin vàlids i calcula la disposició nova del teclat
    public void modificarLayout(int n, int m) throws ExcepcionsCreadorTeclat {
        comprovaLayoutValid(n, m);
        Estrategia estrategia = new BranchandBound();
        dimX = n;
        dimY = m;
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), n, m);
    }
}