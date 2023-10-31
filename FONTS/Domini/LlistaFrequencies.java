package Domini;

import java.util.HashMap;
import java.util.Map;

public class LlistaFrequencies {
    private String nom;
    private Map<String, int> LListaParaules;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrequencies (String nom) {
        this.nom = nom;
        LListaParaules = new HashMap<>();
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrequencies (String nom, Map<String, int> LListaParaules) {
        this.nom = nom;
        this.LListaParaules = LListaParaules;
    }

    // Pre:
    //Post: Es retorna la llista de paraules i frequencies
    public  Map<String, int> getFrequencies() {
        return LlistaParaules;
    }

    /*
    //Pre:
    //Post: Les paraules i frequencies introduides son afegides a la llista
    public insertarFrequencies(Map<string, int> novesEntrades) {
        LlistaFrequencies.union(novesEntrades);
    }
    */
}