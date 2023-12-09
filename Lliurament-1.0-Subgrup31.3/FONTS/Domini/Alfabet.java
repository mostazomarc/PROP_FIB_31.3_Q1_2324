package Domini;

import java.util.Set;
import java.util.HashSet;

public class Alfabet {
    private String nom;
    private Set<Character> lletres;
    private Set<String> idiomes;

    //Pre: lletres conté totes les lletres de l'alfabet que es vol afegir
    //Post: Es crea un alfabet amb el nom i les lletres donades
    public Alfabet (String nom, Set<Character> lletres) {
        this.nom = nom;
        this.lletres = lletres;
        idiomes = new HashSet<String>();
    }

    //Pre: És un nom d'idioma vàlid
    //Post: S'afegeix a idiomes el nomIdioma
    public void afegirIdioma(String nomIdioma) {
        idiomes.add(nomIdioma);
    }

    //Pre: nomIdioma és al set d'idiomes
    //Post: S'elimina d'idiomes el nomIdioma
    public void treureIdioma(String nomIdioma) {
        idiomes.remove(nomIdioma);
    }

    //Retorna el número d'idiomes que utilitzen l'alfabet
    public int numIdiomes() {
        return idiomes.size();
    }

    //Retorna el nom de l'alfabet
    public String getNomAlfabet() { return nom; }

    //Retorna les lletres de l'alfabet
    public Set<Character> getLletres() {
        return lletres;
    }

    //Retorna el número de lletres de l'alfabet
    public int getNumLletres() { return lletres.size(); }

    //Retorna informació (Nom i lletres) de l'Alfabet
    public String getInfo() {
        return "Nom de l'Alfabet: " + nom + "    Lletres (" + getNumLletres() + "): " + lletres;
    }

}

//Classe Programada per: Arnau Tajahuerce