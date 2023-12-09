package Domini;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;
import Excepcions.LlistaBuida;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LlistaFrequencies {
    private final String nom;
    private Map<String, Integer> LlistaParaules;
    private final Idioma idioma;

    //Pre:
    //Post: Es crea una LlistaFrecuencies amb nom i idioma i s'afegeix la llista a l'idioma si l'idioma no té llista predeterminada
    public LlistaFrequencies(String nom, Idioma i) {
        this.nom = nom;
        LlistaParaules = new HashMap<>();
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }


    //Pre: LlistaParaules es una llista valida
    //Post: Es crea una LlistaFrecuencies amb nom, llista paraules i idoma i s'afegeix la llista a l'idioma si l'idioma no té llista predeterminada
    public LlistaFrequencies(String nom, Idioma i, Map<String, Integer> LlistaParaules) throws ExcepcionsCreadorTeclat {
        comprovarLletres(LlistaParaules.keySet(), i);
        this.nom = nom;
        this.LlistaParaules = LlistaParaules;
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }

    public static String quitarTilde(char c) {
        String s = String.valueOf(c);
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("[\\p{M}]");
        Matcher matcher = pattern.matcher(normalized);
        return matcher.replaceAll("");
    }

    //Pre:
    //Post: Comprova que totes les lletres de les paraules estan a lletres de l'idioma (pero les lletres ñ i ç les pasa a n i c llavors no es pot provar aixi)
    private void comprovarLletres(Set<String> paraules, Idioma i) throws ExcepcionsCreadorTeclat {
        Set<Character> lletres = i.getLletres();
        if (paraules.isEmpty()) throw new LlistaBuida();
        for (String paraula : paraules) {
            // obte cada lletra de la paraula
            char[] letrasClave = paraula.toCharArray();

            // Verificar si totes les lletres estan a lletres
            for (char lletra : letrasClave) {
                if (Character.isLetter(lletra)) {
                    lletra = Character.toLowerCase(lletra);
                    if (Character.isUnicodeIdentifierPart(lletra) && !lletres.contains(lletra)) {
                        String caracterSinTilde = quitarTilde(lletra);
                        if (lletra != 'ñ' && lletra != 'ç') lletra = caracterSinTilde.charAt(0);
                        if (!lletres.contains(lletra)) throw new LletraNoInclosa(lletra, i.getNom());
                    }
                }
            }
        }
    }

    //Pre:
    //Post: Es retorna el nom de la llista
    public String getNom() {
        return this.nom;
    }

    // Pre:
    //Post: Es retorna la llista de paraules i frequencies
    public Map<String, Integer> getFrequencies() {
        return LlistaParaules;
    }


    // Pre:
    //Post: Es retorna el nom de l'idioma de la llista'
    public String getNomIdioma() {
        return idioma.getNom();
    }

    //Pre:
    //Post: Es modifica la llista amb les noves entrades: novaLlista, es sobrescriu la llista de paraules anterior
    public void modificarLlista(Map<String, Integer> novaLlista) {
        LlistaParaules = novaLlista;
    }

    /*
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

     */

}

//Classe Programada per: Marc