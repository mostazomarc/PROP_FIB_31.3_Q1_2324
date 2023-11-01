package Domini;

import java.util.HashMap;
import java.util.Map;

public class LlistaFrequencies {
    private String nom;
    private Map<String, Integer> LlistaParaules;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrequencies (String nom) {
        this.nom = nom;
        LlistaParaules = new HashMap<>();
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrequencies (String nom, Map<String, Integer> LlistaParaules) {
        this.nom = nom;
        this.LlistaParaules = LlistaParaules;
    }

    // Pre:
    //Post: Es retorna la llista de paraules i frequencies
    public  Map<String, Integer> getFrequencies() {
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