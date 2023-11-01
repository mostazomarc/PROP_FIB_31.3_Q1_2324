package Domini;

import java.util.HashSet;
import java.util.Set;
//import java.util.Vector;

public class Alfabet {
    private Set<Idioma> idiomes;
    private Set<Character> lletres;

    //Pre: letters conté totes les lletres de l'alfabet que es vol afegir
    //Post: Es crea un alfabet amb Lletres = letters i amb l'idioma donat
    public Alfabet (Idioma language, Set<Character> letters) {
        idiomes.add(language);
        lletres = letters;
    }

    //Pre:
    //Post: s'ha afegit l'idioma language al conjunt d'Idiomes de l'alfabet
    public void afegirIdioma(Idioma language) {
        idiomes.add(language);
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