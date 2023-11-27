package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.IdiomaEnUs;
import Excepcions.LlistaFreqJaExisteix;
import Excepcions.LlistaFreqNoExisteix;

import java.util.HashMap;
import java.util.Map;

/**
 * CtrlPersFreq es una classe que permet guardar i carregar llistes de frequencies
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència de llistes de frequencies</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersFreq {
    /**
     * Instància de CtrlPersFreq
     */
    private static CtrlPersFreq singletonObject;

    //si canviesis de perfil tindires una nova llista
    /**
     * Mapa de llistes de frequencies guardades
     */
    private final Map<String, LlistaFrequencies> frequencies;

    /**
     * Instància de CtrlDomini
     */
    private final CtrlDomini controlador;

    /**
     * Creadora de CtrlPersFreq
     * <p> Crea un conjunt de llistes de frequencies i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersFreq(CtrlDomini c) {
        frequencies = new HashMap<>();
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersFreq, si no existeix cap CtrlPersFreq es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersFreq
     */
    public static CtrlPersFreq getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersFreq(c) {

            };
        return singletonObject;
    }

    /**
     * Comprova que la llista identificada per nomLlista existeix
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    private void comprovaLlistaNoExisteix(String nomLlista) throws LlistaFreqNoExisteix {
        if (!frequencies.containsKey(nomLlista)) throw new LlistaFreqNoExisteix(nomLlista);
    }

    /**
     * Comprova que la llista identificada per nomLlista ja existeix
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    private void comprovaLlistaJaExisteix(String nomLlista) throws LlistaFreqJaExisteix {
        if (frequencies.containsKey(nomLlista)) throw new LlistaFreqJaExisteix(nomLlista);
    }

    /**
     * Carrega les llistes de freqüencia dels arxius on estàn guardades (de moment crea noves, funcionarà al tenir capa de persistencia)
     * @throws Exception
     */
    public void carregarFrequencies() throws Exception {
        Map<String, Integer> novesEntrades = new HashMap<>();
        controlador.novaLlistaPerfil("llista", "catalaFreq.txt", "Català", novesEntrades);

    }


    /**
     * Crea una llista de llistes per al nou perfil i guarda la del perfil anterior si n'hi ha
     * @param usuari El nom d'usuari del perfil
     */
    public void nouPerfil(String usuari) {
    }

    /**
     * Canvia el perfil actual per un altre
     * <p> Guarda la llista de llistes del perfil actual i carrega la del perfil nou</p>
     * @param usuari El nom d'usuari del perfil a canviar
     */
    public void canviaPerfil(String usuari) {

    }

    /**
     * Crea i guarda una nova llista de frequencies amb nomLlista i idioma
     * @param nomLlista El nom de la llista
     * @param i L'idioma de la llista
     * @return La llista de frequencies creada
     * @throws ExcepcionsCreadorTeclat
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i) throws ExcepcionsCreadorTeclat {
        comprovaLlistaJaExisteix(nomLlista);
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista, i);
        frequencies.put(llista.getNom(), llista);
        return llista;
    }

    /**
     * Crea i guarda una nova llista de frequencies amb nomLlista, idioma i novesEntrades
     * @param nomLlista El nom de la llista
     * @param i L'idioma de la llista
     * @param novesEntrades Les noves entrades de la llista
     * @return La llista de frequencies creada
     * @throws ExcepcionsCreadorTeclat
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    public LlistaFrequencies afegirLlistaFreq(String nomLlista, Idioma i, Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        comprovaLlistaJaExisteix(nomLlista);
        LlistaFrequencies llista = new LlistaFrequencies(nomLlista, i, novesEntrades);
        frequencies.put(llista.getNom(), llista);
        return llista;
    }


    /**
     * Afegeix una llista de frequencies
     * @param llista La llista de frequencies a afegir
     * @throws LlistaFreqJaExisteix Si la llista ja existeix
     */
    public void afegirLlistaFreq(LlistaFrequencies llista) throws LlistaFreqJaExisteix{
        comprovaLlistaJaExisteix(llista.getNom());
        frequencies.put(llista.getNom(), llista);
    }

    /**
     * Elimina una llista de frequencies identificada per nomLlista
     * @param nomLlista El nom de la llista a eliminar
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public void eliminarLlista(String nomLlista) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        frequencies.remove(nomLlista);
    }

    // (de moment no comprova llistes de frequencies d'altres usuaris perquè fa falta capa de persistencia)
    /**
     * Comprova que l'idioma identificat per nomIdioma no està en ús en cap llista de frequencies
     * @param nomIdioma El nom de l'idioma
     * @throws IdiomaEnUs Si l'idioma està en ús
     */
    public void comprovarUsIdioma(String nomIdioma) throws IdiomaEnUs {
        for (Map.Entry<String, LlistaFrequencies> llista : frequencies.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma) && !llista.getValue().getNom().equals("LlistaPred"+nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
    }

    /**
     * Obté la llista de frequencies identificada per nomLlista
     * @param nomLlista El nom de la llista
     * @return La llista de frequencies identificada per nomLlista
     * @throws LlistaFreqNoExisteix Si la llista no existeix
     */
    public LlistaFrequencies getLlistaFreq(String nomLlista) throws LlistaFreqNoExisteix {
        comprovaLlistaNoExisteix(nomLlista);
        return frequencies.get(nomLlista);
    }

}


//Classe Programada per: Marc