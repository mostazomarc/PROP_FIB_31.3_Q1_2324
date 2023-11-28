package Domini;

import Excepcions.ExcepcionsCreadorTeclat;

import java.util.Set;
import java.util.Map;

/**
 * Idioma és una classe que conté un alfabet i una llista de paraules i les seves freqüències predeterminada
 * @author Arnau Tajahuerce Brulles (arnau.tajahuerce@estudiantat.upc.edu)
 */
public class Idioma {
    /**
     * El nom de l'idioma
     */
    private String nom;
    /**
     * L'alfabet de l'idioma
     */
    private Alfabet alfabet;
    /**
     * La llista de paraules i freqüències predeterminada de l'idioma
     */
    private LlistaFrequencies llistaFreqPredeterminada;

    /**
     * Creadora de Idioma
     * <p> Crea un idioma amb nom i alfabet</p>
     * <p> Es necessita que l'alfabet proporcionat existeixi i sigui vàlid</p>
     * @param nom El nom de l'idioma
     * @param alfabet L'alfabet de l'idioma
     */
    public Idioma (String nom, Alfabet alfabet) {
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
    }

    /**
     * Creadora de Idioma
     * <p> Crea un idioma amb nom, alfabet i llista de paraules i freqüències predeterminada</p>
     * <p> Es necessita que l'alfabet proporcionat existeixi i sigui vàlid</p>
     * <p> Es necessita que la llista de paraules i freqüències sigui vàlida</p>
     * @param nom El nom de l'idioma
     * @param alfabet L'alfabet de l'idioma
     * @param llistaParaules La llista de paraules i freqüències predeterminada de l'idioma
     * @throws ExcepcionsCreadorTeclat Si la llista de paraules no és vàlida
     */
    public Idioma (String nom, Alfabet alfabet, Map<String, Integer> llistaParaules) throws ExcepcionsCreadorTeclat{
        this.nom = nom;
        this.alfabet = alfabet;
        alfabet.afegirIdioma(nom);
        new LlistaFrequencies("LlistaPred"+nom, this, llistaParaules); //Aquesta creadora ja vincula la llista de freqüències a aquest idioma
    }

    /**
     * Afegeix una llista de paraules i freqüències
     * <p> Es nececssita que la llista de paraules i freqüències proporcionada existeix i és vàlida</p>
     * @param llistaFreq La llista de paraules i freqüències predeterminada de l'idioma
     */
    public void afegirLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        if (llistaFreqPredeterminada == null) llistaFreqPredeterminada = llistaFreq;
    }

    /**
     * Canvia la llista de paraules i freqüències predeterminada: es sobrescriu la llista de paraules i freqüències predeterminada anterior
     * <p> Es necessita que la llista de paraules i freqüències proporcionada existeix i és vàlida</p>
     * @param llistaFreq La nova llista de paraules i freqüències predeterminada
     */
    public void canviarLlistaFreqPredeterminada (LlistaFrequencies llistaFreq) {
        llistaFreqPredeterminada = llistaFreq;
    }

    /**
     * Obté el nom de l'idioma
     * @return El nom de l'idioma
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obté l'alfabet de l'idioma
     * @return L'alfabet de l'idioma
     */
    public Alfabet getAlfabet() { return alfabet; }

    /**
     * Obté les lletres de l'alfabet de l'idioma
     * @return La llista de lletres de l'alfabet de l'idioma
     */
    public Set<Character> getLletres() {
        return alfabet.getLletres();
    }

    /**
     * Obté la llista de freqüencies predeterminada de l'idioma
     * @return La llista de freqüencies predeterminada de l'idioma
     */
    public LlistaFrequencies getLlistaFreq() {
        return llistaFreqPredeterminada;
    }

    /**
     * Obté la llista de paraules i freqüències predeterminada de l'idioma
     * @return La llista de paraules i freqüències predeterminada de l'idioma
     */
    public  Map<String, Integer> getFrequencies() {
        return llistaFreqPredeterminada.getFrequencies();
    }

    /**
     * Obté text amb informació (nom, nom de l'alfabet i nom de la llista de freqüències predeterminada) de l'idioma
     * @return El text amb la informació de l'alfabet
     */
    public String getInfo() {
        return "Nom de l'Idioma: " + nom + "    Nom de l'Alfabet associat: " + alfabet.getNomAlfabet() +
                "    Nom de la Llista de Freqüències predeterminada: " + llistaFreqPredeterminada.getNom();
    }

}