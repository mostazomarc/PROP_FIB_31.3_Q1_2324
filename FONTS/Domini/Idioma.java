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
    //Post: Es crea un Idioma amb nom, alfabet i llista de frequencies predeterimnada
    public Idioma (String nom, Alfabet alfabet, LlistaFrequencies llistaPred) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
        llistaFreqPredeterminada = llistaPred;
    }

    //Pre:
    //Post: Es crea un Idioma amb nom, alfabet i una nova llista de frequencies predeterimnada
    public Idioma (String nom, Alfabet alfabet, String nomLlista, Map<String, Integer> llistaParaules) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
        new LlistaFrequencies("LlistaPred"+nom, this, llistaParaules); //Aquesta creadora ja vincula la llista de freqüències a aquest idioma
    }

    //Pre: No existeix un idioma amb nomIdioma. Existeix l'alfabet alphabet. Existeix la llista de freqüències FreqList
    //Post: S'ha creat un idioma amb nom = nomIdioma, alfabet = alphabet i llistaFreq = FreqList
    //      i s'afegeix aquest idioma a l'alfabet donat
    public void afegirLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        if (llistaFreqPredeterminada == null) llistaFreqPredeterminada = llistaFreq;
    }

    public void canviarLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
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

    //Retorna la llista de paraules amb frequències de l
    public  Map<String, Integer> getFrequencies() {
        return llistaFreqPredeterminada.getFrequencies();
    }

    //Retorna informació (Nom de l'Idioma, nom de l'Alfabet i nom de la Llista de Freqüències predeterminada) de l'Idioma
    public String getInfo() {
        return "Nom de l'Idioma: " + nom + "    Nom de l'Alfabet associat: " + alfabet.getNomAlfabet() +
                "    Nom de la Llista de Freqüències predeterminada: " + llistaFreqPredeterminada.getNom();
    }

}

//Classe Programada per: Arnau Tajahuerce