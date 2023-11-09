package Domini;

import java.util.HashMap;
import java.util.Map;

public class LlistaFrequencies {
    private String nom;
    private Map<String, Integer> LlistaParaules;
    Idioma idioma;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrequencies (String nom, Idioma i) {
        this.nom = nom;
        LlistaParaules = new HashMap<>();
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrequencies (String nom, Idioma i, Map<String, Integer> LlistaParaules) {
        this.nom = nom;
        this.LlistaParaules = LlistaParaules;
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }

    //Pre:
    //Post: Es retorna el nom de la llista
    public String getNom() {
        return this.nom;
    }

    // Pre:
    //Post: Es retorna la llista de paraules i frequencies
    public  Map<String, Integer> getFrequencies() {
        return LlistaParaules;
    }


    //Pre:
    //Post: Les paraules i frequencies introduides son afegides a la llista
    public void insertarFrequencies(Map<String, Integer> novesEntrades) {

        //iterar per cada nova entrada i comprovar si la paraula ja existeix
        for (Map.Entry<String, Integer> entrada : novesEntrades.entrySet()) {
            String paraula = entrada.getKey();
            Integer freq = entrada.getValue();

            if (LlistaParaules.containsKey(paraula)) {
                // Si la paraula ja existeix obtenir frequencia actual
                int FrecVella = LlistaParaules.get(paraula);
                // Sumar la nova vella frequencia a la nova
                freq += FrecVella;
            }



            LlistaParaules.put(paraula,freq);
        }


    }

}