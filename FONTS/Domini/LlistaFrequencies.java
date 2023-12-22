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

/**
 * LlistaFrequencies es una classe que conte una llista de paraules i les seves frequencies
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class LlistaFrequencies {
    /**
     * El nom de la llista
     */
    private final String nom;
    /**
     * La llista de paraules i frequencies
     */
    private Map<String, Integer> LlistaParaules;
    /**
     * L'idioma de la llista
     */
    private final Idioma idioma;


    /**
     * Creadora de LlistaFrequencies
     * <p> Crea una llista de frequencies amb nom i idioma i afegeix la llista a un idioma com a predeterminada si aquest no té cap</p>
     * <p> Es necessita que l'idioma proporcionat existeixi i sigui vàlid</p>
     * @param nom El nom de la llista
     * @param i L'idioma de la llista
     */
    public LlistaFrequencies(String nom, Idioma i) {
        this.nom = nom;
        LlistaParaules = new HashMap<>();
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }

    /**
     * Creadora de LlistaFrequencies
     * <p> Crea una llista de frequencies amb nom, idioma i una llista de paraules i frequencies i afegeix la llista a un idioma com a predeterminada si aquest no té cap</p>
     * <p> Es necessita que l'idioma proporcionat existeixi i sigui vàlid</p>
     * <p> Es necessita que la llista de paraules sigui vàlida</p>
     * @param nom El nom de la llista
     * @param i L'idioma de la llista
     * @param LlistaParaules La llista de paraules i frequencies
     * @throws ExcepcionsCreadorTeclat Si la llista de paraules no és vàlida
     */
    public LlistaFrequencies(String nom, Idioma i, Map<String, Integer> LlistaParaules) throws ExcepcionsCreadorTeclat {
        comprovarLletres(LlistaParaules.keySet(), i);
        this.nom = nom;
        this.LlistaParaules = LlistaParaules;
        idioma = i;
        i.afegirLlistaFreqPredeterminada(this);
    }

    /**
     * Treu la tilde a un caracter
     * @param c El caracter a treure la tilde
     * @return El caracter sense tilde
     */
    public static String quitarTilde(char c) {
        String s = String.valueOf(c);
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("[\\p{M}]");
        Matcher matcher = pattern.matcher(normalized);
        return matcher.replaceAll("");
    }

    /**
     * Comprova que totes les lletres de les paraules estan a lletres de lalfabet de l'idioma
     * @param paraules La llista de paraules a cmprovar
     * @param i L'idioma de la llista
     * @throws LlistaBuida Si la llista de paraules es buida
     * @throws LletraNoInclosa Si alguna lletra de les paraules no està a l'alfabet de l'idioma
     */
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

    /**
     * Obté el nom de la llista
     * @return El nom de la llista
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obté la llista de paraules i frequencies
     * @return La llista de paraules i frequencies
     */
    public Map<String, Integer> getFrequencies() {
        return LlistaParaules;
    }

    /**
     * Obté el nom de l'idioma de la llista
     * @return El nom de l'idioma de la llista
     */
    public String getNomIdioma() {
        return idioma.getNom();
    }

    /**
     * Modifica la llista amb les noves entrades: novaLlista, es sobrescriu la llista de paraules anterior
     * @param novaLlista La nova llista de paraules i frequencies
     */
    public void modificarLlista(Map<String, Integer> novaLlista) {
        LlistaParaules = novaLlista;
    }

}