package Domini;

import java.util.HashSet;
import java.util.Set;
//import java.util.Vector;

public class Alfabet {
    private String idioma;
    private Set<char> lletres;

    //Pre: lletres conté totes les lletres de l'alfabet donat
    //Post: Es crea un alfabet d'idioma = idioma y lletres=lletres
    public Alfabet (String idioma, Set<char> lletres) {
        this.idioma = idioma;
        this.lletres = lletres;
    }

    //pre:
    //Post: Es crea un alfabet idioma = idioma
    public Alfabet (String idioma) {
        this.idioma = idioma;
    }

    //Retorna idioma de l'alfabet
    public String getIdioma() {
        return idioma;
    }

    //Retorna les lletres de l'alfabet
    public set<char> getLletres() {
        return lletres
    }


}