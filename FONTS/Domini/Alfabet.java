package Domini;

import java.util.Set;
import java.util.HashSet;

public class Alfabet {
    private Set<Idioma> idiomes;
    private Set<Character> lletres;

    //Pre: letters conté totes les lletres de l'alfabet que es vol afegir
    //Post: Es crea un alfabet amb Lletres = letters i amb l'idioma donat
    public Alfabet (Idioma idioma, Set<Character> lletres) {
        idiomes = new HashSet<Idioma>();
        idiomes.add(idioma);
        this.lletres = lletres;
    }

    //Pre:
    //Post: s'ha afegit l'idioma language al conjunt d'Idiomes de l'alfabet
    public void afegirIdioma(Idioma idioma) {
        idiomes.add(idioma);
    }

    //Retorna les lletres de l'alfabet
    public Set<Character> getLletres() {
        return lletres;
    }

    //Retorna els idiomes de l'alfabet
    public Set<Idioma> getIdiomes() {
        return idiomes;
    }
}