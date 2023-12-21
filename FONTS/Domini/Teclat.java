package Domini;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.*;

import java.util.*;

/**
 * Teclat és una classe que conté una disposició de teclat, un idioma i una llista de freqüències
 *
 * @author Agustí Costabella (agusti.costabella@estudiantat.upc.edu)
 */
public class Teclat {
    /**
     * El nom del teclat
     */
    private String nom;
    /**
     * El nombre de columnes del teclat
     */
    private int dimX;
    /**
     * El nombre de files del teclat
     */
    private int dimY;
    /**
     * La disposició del teclat
     */
    private char[][] disposicio;
    /**
     * L'idioma del teclat
     */
    private Idioma idioma;
    /**
     * La llista de freqüències del teclat
     */
    private LlistaFrequencies llistafreq;
    /**
     * L'algorisme emprat per a construir el teclat
     */
    private Estrategia estrategia;

    /**
     * Comprova que el layout sigui vàlid
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws LayoutNoValid Si el layout no és vàlid
     * @throws LayoutMassaGran Si el layout és massa gran
     */
    private void comprovaLayoutValid(int n, int m) throws LayoutNoValid, LayoutMassaGran {
        int totalLletres = idioma.getLletres().size();
        int lletresRequerides = n * m;

        if (totalLletres > lletresRequerides) {
            throw new LayoutNoValid(totalLletres, n, m);
        } else if (totalLletres < lletresRequerides) {
            int posbuides = n*m - totalLletres;
            if (posbuides > 0) {
                if (n == 1 && m > totalLletres) throw new LayoutMassaGran(totalLletres, n, m);
                else if (m == 1 && n > totalLletres) throw new LayoutMassaGran(totalLletres, n, m);
                else if (n == m && posbuides >= n) throw new LayoutMassaGran(totalLletres, n, m);
                else if (posbuides >= m) throw new LayoutMassaGran(totalLletres, n, m);
            }
        }
    }

    /**
     * Comprova que els idiomes siguin iguals entre la llista i el teclat
     * @param nomidiomallista El nom de l'idioma de la llista
     * @param nomidiomateclat El nom de l'idioma del teclat
     * @throws IdiomesDiferents Si els idiomes són diferents
     */
    private void comprovaIdiomes(String nomidiomallista, String nomidiomateclat) throws IdiomesDiferents {
        if (nomidiomallista != nomidiomateclat) throw new IdiomesDiferents(nomidiomallista, nomidiomateclat);
    }

    /**
     * Creadora de Teclat amb llista de frequencies
     * @param nom El nom del teclat
     * @param llistafreq La llista de freqüències del teclat
     * @param i L'idioma del teclat
     * @param n El nombre de columnes del teclat
     * @param m El nombre de files del teclat
     * @throws ExcepcionsCreadorTeclat Si el layout no és vàlid o els idiomes són diferents
     */
    public Teclat(String nom, LlistaFrequencies llistafreq, Idioma i, int n, int m, String e) throws ExcepcionsCreadorTeclat{
        comprovaIdiomes(llistafreq.getNomIdioma(),i.getNom());
        this.nom = nom;
        idioma = i;
        this.llistafreq = llistafreq;
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        if (e.equals("BranchAndBound")){
            estrategia = new BranchandBound();
        }
        else if (e.equals("GeneticAlgorithm")){
            estrategia = new Genetic();
        }
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), dimX, dimY);

    }

    /**
     * Creadora de Teclat amb llista de frequencies predeterminada d'un idioma
     * @param nom El nom del teclat
     * @param i L'idioma del teclat
     * @param n El nombre de columnes del teclat
     * @param m El nombre de files del teclat
     * @throws ExcepcionsCreadorTeclat Si el layout no és vàlid
     */
    public Teclat(String nom, Idioma i, int n, int m, String e) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        idioma = i;
        comprovaLayoutValid(n,m);
        dimX = n;
        dimY = m;
        this.llistafreq = i.getLlistaFreq();
        if (e.equals("BranchAndBound")){
            estrategia = new BranchandBound();
        }
        else if (e.equals("GeneticAlgorithm")){
            estrategia = new Genetic();
        }
        disposicio = estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), dimX, dimY);
    }

    /**
     * Creadora de Teclat amb disposició ja feta
     * @param nom El nom del teclat
     * @param llista La llista de freqüències del teclat
     * @param i L'idioma del teclat
     * @param n El nombre de columnes del teclat
     * @param m El nombre de files del teclat
     * @param disposicio La disposició del teclat
     */
    public Teclat (String nom,LlistaFrequencies llista, Idioma i, int n, int m, char[][] disposicio) {
        this.nom = nom;
        idioma = i;
        this.llistafreq = llista;
        this.disposicio = disposicio;
        dimX = n;
        dimY = m;
    }

    /**
     * Retorna el nom de la llista de freqüències
     * @return El nom de la llista de freqüències
     */
    public String getNomLlistaFreq() {return llistafreq.getNom(); }

    /**
     * Retorna el nom del teclat
     * @return El nom del teclat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna la disposició de teclat
     * @return La disposició de teclat
     */
    public char[][] getDisposicio() {
        return disposicio;
    }

    /**
     * Retorna el nom de l'idioma del teclat
     * @return El nom de l'idioma del teclat
     */
    public String getNomIdioma() {
        return idioma.getNom();
    }

    /**
     * Retorna el nombre de files del teclat
     * @return El nombre de files del teclat
     */
    public Integer getDimX() {return dimX; }

    /**
     * Retorna el nombre de columnes del teclat
     * @return El nombre de columnes del teclat
     */
    public Integer getDimY() {return dimY; }

    /**
     * Modifica el layout del teclat
     * @param n El nou nombre de files
     * @param m El nou nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el layout no és vàlid
     */
    public void modificarLayout(int n, int m) throws ExcepcionsCreadorTeclat {
        comprovaLayoutValid(n, m);
        dimX = n;
        dimY = m;
        disposicio = this.estrategia.calculaDisposicio(this.llistafreq.getFrequencies(), idioma.getLletres(), n, m);
    }
}

//Classe Programada per: Agustí Costabella