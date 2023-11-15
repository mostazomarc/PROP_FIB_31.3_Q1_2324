package Stubs;


import java.util.HashMap;
import java.util.Map;

public class LlistaFrequenciesStub {
    private String nom;
    private Map<String, Integer> LlistaParaules;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrequenciesStub (String nom) {
    }

    public LlistaFrequenciesStub () {
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrequenciesStub (String nom, Map<String, Integer> LlistaParaules) {
    }

    //Pre:
    //Post: Es retorna el nom de la llista
    public String getNom() {
        return "LlistaProva";
    }

    // Pre:
    //Post: Es retorna la llista de paraules i frequencies
    public  Map<String, Integer> getFrequencies() {
        Map<String, Integer> llistaParaulesProva = new HashMap<>();
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
        return llistaParaulesProva;
    }


    //Pre:
    //Post: Les paraules i frequencies introduides son afegides a la llista
    public void insertarFrequencies(Map<String, Integer> novesEntrades) {}

}

//Classe Programada per: Marc