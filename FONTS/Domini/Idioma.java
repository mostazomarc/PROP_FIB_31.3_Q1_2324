package Domini;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class Idioma {
    private String nom;
    private Alfabet alfabet;
    private LlistaFrequencies llistaFreqPredeterminada;

    //Pre:
    //Post: Es crea un Idioma amb nom i alfabet
    public Idioma (String nom, Alfabet alfabet) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
    }

    //Pre:
    //Post: Es crea un Idioma amb nom, alfabet i llista de freqüències predeterimnada
    public Idioma (String nom, Alfabet alfabet, LlistaFrequencies llistaPred) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
        llistaFreqPredeterminada = llistaPred;
    }

    //Pre:
    //Post: Es crea un Idioma amb nom, alfabet i una NOVA llista de freqüències predeterimnada
    public Idioma (String nom, Alfabet alfabet, Map<String, Integer> llistaParaules) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
        new LlistaFrequencies("LlistaPred"+nom, this, llistaParaules); //Aquesta creadora ja vincula la llista de freqüències a aquest idioma
    }

    //Pre: La llistaFreq existeix
    //Post: S'afegeix la llistaFreq com a llista de freqüències predeterminada de l'idioma
    public void afegirLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        if (llistaFreqPredeterminada == null) llistaFreqPredeterminada = llistaFreq;
    }

    //Pre: La llistaFreq existeix
    //Post: llistaFreq és ara la nova llista de freqüències predeterminada de l'idioma
    public void canviarLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        llistaFreqPredeterminada = llistaFreq;
    }

    //Retorna el nom de l'idioma
    public String getNom() {
        return nom;
    }

    //Retorna l'alfabet de l'idioma
    public Alfabet getAlfabet() { return alfabet; }

    //Retorna les lletres que té l'alfabet de l'idioma
    public Set<Character> getLletres() {
        return alfabet.getLletres();
    }

    //Retorna la llista de frequencies de l'idioma
    public LlistaFrequencies getLlistaFreq() {
        return llistaFreqPredeterminada;
    }

    //Retorna la llista de paraules amb freqüències de l
    public  Map<String, Integer> getFrequencies() {
        return llistaFreqPredeterminada.getFrequencies();
    }

    //Retorna informació (nom, nom de l'alfabet i nom de la llista de freqüències predeterminada) de l'idioma
    public String getInfo() {
        return "Nom de l'Idioma: " + nom + "    Nom de l'Alfabet associat: " + alfabet.getNomAlfabet() +
                "    Nom de la Llista de Freqüències predeterminada: " + llistaFreqPredeterminada.getNom();
    }

}

//Classe Programada per: Arnau Tajahuerce