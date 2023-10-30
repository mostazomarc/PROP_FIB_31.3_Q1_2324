package Domini;

import java.util.HashMap;
import java.util.Map;

public class LlistaFrecuencies {
    private String nom;
    private Map<String, int> LListaParaules;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrecuencies (String nom) {
        this.nom = nom;
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrecuencies (String nom, Map<String, int> LListaParaules) {
        this.nom = nom;
        this.LListaParaules = LListaParaules;
    }

    public  Map<String, int> getFrecuencies() {
        return LlistaParaules;
    }

}