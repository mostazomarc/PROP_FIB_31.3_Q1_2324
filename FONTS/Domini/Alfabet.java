package Domini;

import java.util.Set;
import java.util.HashSet;

/**
 * Alfabet es una classe que conte una llista de lletres
 * @author Arnau Tajahuerce Brulles (arnau.tajahuerce@estudiantat.upc.edu)
 */
public class Alfabet {
    /**
     * El nom de l'alfabet
     */
    private String nom;
    /**
     * La llista de lletres
     */
    private Set<Character> lletres;
    /**
     * Els noms dels idiomes que tenen l'alfabet
     */
    private Set<String> idiomes;

    /**
     * Creadora de Alfabet
     * <p> Crea un alfabet amb nom i lletres</p>
     * <p> Es necessita que el set de lletres només contingui les lletres de l'alfabet</p>
     * @param nom El nom de l'alfabet
     * @param lletres Les lletres de l'alfabet
     */
    public Alfabet (String nom, Set<Character> lletres) {
        this.nom = nom;
        this.lletres = lletres;
        idiomes = new HashSet<String>();
    }

    /**
     * Afegeix a la llista de noms d'idiomes que tenen l'alfabet el nom de l'idioma donat
     * <p> Es necessita que l'idioma amb el nom proporcionat sigui un idioma del sistema</p>
     * @param nomIdioma El nom de l'idioma que es vol afegir
     */
    public void afegirIdioma(String nomIdioma) {
        idiomes.add(nomIdioma);
    }

    /**
     * Treu de la llista de noms d'idiomes que utilitzen l'alfabet el nom de l'idioma donat
     * <p> Es necessita que l'idioma amb el nom proporcionat estigui a la llista de noms d'idiomes que tenen l'alfabet</p>
     * @param nomIdioma El nom de l'idioma que es vol treure
     */
    public void treureIdioma(String nomIdioma) {
        idiomes.remove(nomIdioma);
    }

    /**
     * Obté el nombre d'idiomes que tenen l'alfabet
     * @return El nombre d'idiomes que tenen l'alfabet
     */
    public int numIdiomes() {
        return idiomes.size();
    }

    /**
     * Obté el nom de l'alfabet
     * @return El nom de l'alfabet
     */
    public String getNomAlfabet() { return nom; }

    /**
     * Obté les lletres de l'alfabet
     * @return La llista de lletres de l'alfabet
     */
    public Set<Character> getLletres() {
        return lletres;
    }

    /**
     * Obté el nombre de lletres que té l'alfabet
     * @return El nombre de lletres que té l'alfabet
     */
    public int getNumLletres() { return lletres.size(); }

    /**
     * Obté text amb informació (nom i lletres) de l'alfabet
     * @return El text amb la informació de l'alfabet
     */
    public String getInfo() {
        return "Nom de l'Alfabet: " + nom + "    Lletres (" + getNumLletres() + "): " + lletres;
    }

}