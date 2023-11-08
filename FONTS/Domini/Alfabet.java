package Domini;

import java.util.Set;
import java.util.HashSet;

public class Alfabet {
    private String nomAlfabet;
    private Set<Character> lletres;

    public Alfabet() {
        nomAlfabet = "";
        lletres = new HashSet<Character>();
    }

    //Pre: letters conté totes les lletres de l'alfabet que es vol afegir
    //Post: Es crea un alfabet amb Lletres = letters i amb l'idioma donat
    public Alfabet (String nomAlfabet, Set<Character> lletres) {
        this.nomAlfabet = nomAlfabet;
        this.lletres = lletres;
    }

    //Retorna el nom de l'alfabet
    public String getNomAlfabet() { return nomAlfabet; }

    //Retorna les lletres de l'alfabet
    public Set<Character> getLletres() {
        return lletres;
    }

}