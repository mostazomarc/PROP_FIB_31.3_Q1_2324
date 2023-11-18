package Domini;

import java.util.Set;
import java.util.HashSet;

public class Alfabet {
    private String nomAlfabet;
    private Set<Character> lletres;
    private Set<String> idiomes;

    public Alfabet() {
        nomAlfabet = "";
        lletres = new HashSet<Character>();
    }

    //Pre: letters conté totes les lletres de l'alfabet que es vol afegir
    //Post: Es crea un alfabet amb Lletres = letters i amb l'idioma donat
    public Alfabet (String nomAlfabet, Set<Character> lletres) {
        this.nomAlfabet = nomAlfabet;
        this.lletres = lletres;
        idiomes = new HashSet<String>();
    }

    public void afegirIdioma(String nomIdioma) {
        idiomes.add(nomIdioma);
    }

    public void treureIdioma(String nomIdioma) {
        idiomes.remove(nomIdioma);
    }

    public int numIdiomes() {
        return idiomes.size();
    }

    public boolean teIdioma(String nomIdioma) {
        return idiomes.contains(nomIdioma);
    }

    //Retorna el nom de l'alfabet
    public String getNomAlfabet() { return nomAlfabet; }

    //Retorna les lletres de l'alfabet
    public Set<Character> getLletres() {
        return lletres;
    }

    public int getNumLletres() { return lletres.size(); }

    //Retorna informació (Nom i lletres) de l'Alfabet
    public String getInfo() {
        return "Nom de l'Alfabet: " + nomAlfabet + "    Lletres (" + getNumLletres() + "): " + lletres;
    }

}

//Classe Programada per: Arnau