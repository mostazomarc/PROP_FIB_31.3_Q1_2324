package Domini;

import java.util.Set;
import java.util.HashSet;

public class Idioma {
    private String nom;
    private Alfabet alfabet;
    private LlistaFrequencies llistaFreqPredeterminada;

    public Idioma (String nom, Alfabet alfabet) {
        this.nom = nom;
        this.alfabet = alfabet;
    }

    //Pre: No existeix un idioma amb nomIdioma. Existeix l'alfabet alphabet. Existeix la llista de freqüències FreqList
    //Post: S'ha creat un idioma amb nom = nomIdioma, alfabet = alphabet i llistaFreq = FreqList
    //      i s'afegeix aquest idioma a l'alfabet donat
    public void afegirLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        llistaFreqPredeterminada = llistaFreq;
    }

    //Retorna el nom de l'idioma
    public String getNom() {
        return nom;
    }

    public Alfabet getAlfabet() { return alfabet; }

    //Retorna les lletres que té l'alfabet de l'idioma
    public Set<Character> getLletres() {
        return alfabet.getLletres();
    }

    //Retorna la llista de frequències de l'idioma
    public LlistaFrequencies getLlistaFreq() {
        return llistaFreqPredeterminada;
    }

}