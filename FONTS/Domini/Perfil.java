package Domini;

import ControladorsDomini.CtrlDomini;
import Excepcions.*;

import java.util.*;

/**
 * Perfil es una classe que conte les llistes de frequencies i els teclats creats per l'usuari
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class Perfil {
    /**
     * El nom d'usuari del perfil
     */
    private String Usuari;
    /**
     * Les llistes de frequencies del perfil
     */
    private final Map<String, LlistaFrequencies> frequencies;
    /**
     * Els teclats creats pel perfil
     */
    private final Map<String, Teclat> teclats;

    /**
     * Creadora de Perfil
     * <p> Crea un perfil amb nom d'usuari</p>
     * @param User El nom d'usuari del perfil
     */
    public Perfil(String User) {
        Usuari = User;
        frequencies = new HashMap<>();
        teclats = new HashMap<>();
    }

    /**
     * Comprova si la llista identificada per nomLlista existeix
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    /**
     * Comprova si la llista identificada per nomLlista ja existeix
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }

    /**
     * Obté el nom d'usuari del perfil
     * @return El nom d'usuari del perfil
     */
    public String getUsuari() {
        return Usuari;
    }

    /**
     * Canvia el nom d'usuari del perfil
     * @param newUs El nou nom d'usuari
     */
    public void canviaUsuari(String newUs) {
        Usuari = newUs;
    }

    /**
     * Creadora de LlistaFrequencies
     * <p> Crea una llista de frequencies amb nom: nomLlista, a partir de l'idioma idioma i amb les entrades novesEntrades i la guarda al perfil</p>
     * @param nomLlista El nom de la llista
     * @param i L'idioma de la llista
     * @param novesEntrades Les noves entrades
     * @return La llista de frequencies creada
     * @throws ExcepcionsCreadorTeclat Si la llista ja existeix o algun altre error en crear la llista
     */
    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i, Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        comprovaLlistaJaExisteix(nomLlista);
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista, i, novesEntrades);
        frequencies.put(llista.getNom(), llista);
        return llista;
    }



    /**
     * S'elimina la llista indicada per nomLlista
     * @param nomLlista El nom de la llista
     * @throws ExcepcionsCreadorTeclat Si la llista no existeix o si la llista esta sent utilitzada per algun teclat
     * @throws LlistaFreqEnUs Si la llista esta sent utilitzada per algun teclat
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public void eliminaLlista(String nomLlista) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(nomLlista);
        for (Map.Entry<String, Teclat> Teclat : teclats.entrySet()) {
            String nom = Teclat.getKey();
            Teclat teclat = Teclat.getValue();
            if (teclat.getNomLlistaFreq().equals(nomLlista)) throw new LlistaFreqEnUs(nomLlista);
        }
        frequencies.remove(nomLlista);
    }

    /**
     * S'afegeixen les noves entrades a la llista indicada per nomLlista
     * @param nomLlista El nom de la llista
     * @param novesEntrades Les noves entrades
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public void modificarLlista(String nomLlista, Map<String, Integer> novesEntrades) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        frequencies.get(nomLlista).modificarLlista(novesEntrades);
    }

    /**
     * S'obté una llista amb els noms de totes les llistes del perfil
     * @return Una llista amb els noms de totes les llistes del perfil
     */
    public List<String> getNomAllLlistes() {
        Set<String> noms = frequencies.keySet();
        return new ArrayList<>(noms);
    }

    /**
     * S'obté el nom de l'idioma de la llista amb nom nomLlista
     * @param nomLlista El nom de la llista
     * @return El nom de l'idioma de la llista amb nom nomLlista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public String getNomIdiomaLlista(String nomLlista) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        return frequencies.get(nomLlista).getNomIdioma();
    }

    /**
     * S'obté la llista de paraules i les seves frequencies amb nom nomSeleccio
     * @param nomSeleccio El nom de la llista
     * @return La llista de paraules i les seves frequencies amb nom nomSeleccio
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomSeleccio);
        return frequencies.get(nomSeleccio).getFrequencies();
    }


    /**
     * Creadora de Teclat
     * <p> Crea un teclat amb nom: NomTeclat i a partir de la llista identificada per NomLlista, l'idioma idioma i amb layout n*m i el guarda al perfil</p>
     * @param NomTeclat El nom del teclat
     * @param NomLlista El nom de la llista
     * @param idioma L'idioma del teclat
     * @param n El nombre de files del teclat
     * @param m El nombre de columnes del teclat
     * @return El teclat creat
     * @throws ExcepcionsCreadorTeclat Si la llista no existeix o si el teclat ja existeix
     * @throws TeclatJaExisteix Si el teclat ja existeix
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public Teclat crearTeclatLlistaPropia(String NomTeclat, String NomLlista, Idioma idioma, int n, int m, String e) throws ExcepcionsCreadorTeclat {
        comprovaLlistaNoExisteix(NomLlista);
        if (teclats.containsKey(NomTeclat)) throw new TeclatJaExisteix(NomTeclat);
        teclats.put(NomTeclat, new Teclat(NomTeclat, frequencies.get(NomLlista), idioma, n, m, e));
        return teclats.get(NomTeclat);
    }

    /**
     * Creadora de Teclat
     * <p> Crea un teclat amb nom: NomTeclat i a partir de la llista de l'idioma i l'idioma idioma i amb layout n*m i el guarda al perfil</p>
     * @param NomTeclat El nom del teclat
     * @param i L'idioma del teclat
     * @param n El nombre de files del teclat
     * @param m El nombre de columnes del teclat
     * @return El teclat creat
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix
     * @throws TeclatJaExisteix Si el teclat ja existeix
     */
    public Teclat crearTeclatLlistaIdioma(String NomTeclat, Idioma i, int n, int m, String e) throws ExcepcionsCreadorTeclat {
        if (teclats.containsKey(NomTeclat)) throw new TeclatJaExisteix(NomTeclat);
        teclats.put(NomTeclat, new Teclat(NomTeclat, i, n, m, e));
        return teclats.get(NomTeclat);
    }

    public Teclat afegirTeclat(String nomTeclat, LlistaFrequencies llista, Idioma i, int n, int m,String estrategia, char[][] disposicio) throws ExcepcionsCreadorTeclat {
        if (teclats.containsKey(nomTeclat)) throw new TeclatJaExisteix(nomTeclat);
        Teclat nouTeclat = new Teclat(nomTeclat,llista,i,n,m,estrategia,disposicio);
        teclats.put(nomTeclat,nouTeclat);
        return nouTeclat;
    }

    /**
     * S'elimina el teclat identificat per NomTeclat
     * @param NomTeclat El nom del teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public void eliminarTeclat(String NomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(NomTeclat)) throw new TeclatNoExisteix(NomTeclat);
        teclats.remove(NomTeclat);
    }

    /**
     * S'obté una llista amb els noms de tots els teclats del perfil
     * @return Una llista amb els noms de tots els teclats del perfil
     */
    public List<String> getNomsTeclats() {
        Set<String> noms = teclats.keySet();
        return new ArrayList<>(noms);
    }

    /**
     * S'obté el nom de l'idioma del teclat identificat per nomTeclat
     * @param nomTeclat El nom del teclat
     * @return El nom de l'idioma del teclat identificat per nomTeclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getIdiomaTeclat(String nomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getNomIdioma();
    }

    /**
     * S'obté el nom de la llista utilitzada per crear el teclat identificat per nomTeclat
     * @param nomTeclat El nom del teclat
     * @return El nom de la llista utilitzada per crear el teclat identificat per nomTeclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getLlistaTeclat(String nomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getNomLlistaFreq();
    }

    /**
     * S'obté la disposició del teclat identficat per nomTeclat
     * @param nomTeclat El nom del teclat
     * @return La disposició del teclat identficat per nomTeclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public char[][] consultaTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat).getDisposicio();
    }

    /**
     * Es modifica el layout del teclat identificat per nomTeclat per un de n*m
     * @param nomTeclat El nom del teclat
     * @param n El nombre de files del teclat
     * @param m El nombre de columnes del teclat
     * @throws ExcepcionsCreadorTeclat Excepcio creador teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public void modificarLayoutTeclat(String nomTeclat, int n, int m) throws ExcepcionsCreadorTeclat {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
        teclats.get(nomTeclat).modificarLayout(n, m);
    }

}