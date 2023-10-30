package Domini;

import java.util.HashMap;
import java.util.Map;
import java.util.scanner;

public class LlistaFrequencies {
    private String nom;
    private Map<String, int> LListaParaules;


    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom
    public LlistaFrequencies (String nom) {
        this.nom = nom;
        LListaParaules = new HashMap<>;
    }

    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom i llista paraules
    public LlistaFrequencies (String nom, Map<String, int> LListaParaules) {
        this.nom = nom;
        this.LListaParaules = LListaParaules;
    }

    public  Map<String, int> getFrequencies() {
        return LlistaParaules;
    }

    //Pre:
    //Post: Les paraules i frequencies introduides son afegides a la llista
    //AIXO CREC QUE ANIRIA EN ALTRE LLOC LO DEL INPUT I TAL
    public insertarFrequencies() {
        System.out.println("*Insertar frequencies* \nIntrodueix: Paraula i freqüencia:\n En acabar escrigui 'd' i per cancelar escrigui 'x'\n");
        Map<string, int> novesEntrades = new HashMap<>;
        Scanner sc = new Scanner(System.in); //Se crea el lector
        String paraula = sc.nextLine();//llegir paraula
        while (paraula != "d" and paraula != "x") {
            int freq = sc.nextLine(); //legir frequencia
            novesEntrades.putAll(paraula, freq); //afegir paraula i frecuencia a la llista
            paraula = sc.nextLine(); //llegir nova paraula
        }
        if (paraula == "d") LlistaFrequencies.union(novesEntrades);
    }
}