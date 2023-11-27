package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Teclat;
import Excepcions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * CtrlPersTeclats es una classe que permet guardar i carregar teclats
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència de teclats</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersTeclats {
    /**
     * Instància de CtrlPersTeclats
     */
    private static CtrlPersTeclats singletonObject;
    /**
     * Instància de CtrlDomini
     */
    CtrlDomini controlador;
    //si canviesis de perfil tindires una nova llista de teclats
    /**
     * Mapa de teclats guardats
     */
    private final Map<String, Teclat> teclats;


    /**
     * Creadora de CtrlPersTeclats
     * <p> Crea un conjunt de teclats i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersTeclats(CtrlDomini c) {
        teclats = new HashMap<>();
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersTeclats, si no existeix cap CtrlPersTeclats es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersTeclats
     */
    public static CtrlPersTeclats getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersTeclats(c) {

            };
        return singletonObject;
    }

    /**
     * Comprova que el teclat identificat per nomTeclat existeix
     * @param nomTeclat El nom del teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    private void comprovaTeclatNoExisteix(String nomTeclat) throws TeclatNoExisteix {
        if (!teclats.containsKey(nomTeclat)) throw new TeclatNoExisteix(nomTeclat);
    }


    /**
     * Comprova que el teclat identificat per nomTeclat ja existeix
     * @param nomTeclat El nom del teclat
     * @throws TeclatJaExisteix Si el teclat ja existeix
     */
    private void comprovaTeclatJaExisteix(String nomTeclat) throws TeclatJaExisteix {
        if (teclats.containsKey(nomTeclat)) throw new TeclatJaExisteix(nomTeclat);
    }

    /**
     * Carrega els teclats dels arxius on estàn guardats (de moment crea nous, funcionarà al tenir capa de persistencia)
     *
     */
    public void carregarTeclats()  {


    }


    /**
     * Crea una llista de teclats per al nou perfil i guarda la del perfil anterior si n'hi ha
     * @param usuari El nom d'usuari del perfil
     */
    public void nouPerfil(String usuari) {
    }

    /**
     * Canvia el perfil actual per un altre
     * <p> Guarda el conjunt de teclats del perfil actual i carrega la del perfil nou</p>
     * @param usuari El nom d'usuari del perfil
     */
    public void canviaPerfil(String usuari) {

    }

    /**
     * Guarda el teclat t al conjunt de teclats
     * @param t El teclat a guardar
     * @throws TeclatJaExisteix Si el teclat ja existeix
     */
    public void afegirTeclat(Teclat t) throws TeclatJaExisteix {
        comprovaTeclatJaExisteix(t.getNom());
        teclats.put(t.getNom(), t);
    }

    /**
     * Elimina el teclat identificat per nomTeclat de el conjunt de teclats
     * @param nomTeclat El nom del teclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public void eliminarTeclat(String nomTeclat) throws TeclatNoExisteix {
        comprovaTeclatNoExisteix(nomTeclat);
        teclats.remove(nomTeclat);
    }


    /**
     * Comprova que l'idioma identificat per nomIdioma no està en ús en cap teclat
     * (de moment no comprova teclats d'altres usuaris perquè fa falta capa de persistencia)
     * @param nomIdioma El nom de l'idioma
     * @throws IdiomaEnUs Si l'idioma està en ús per algun teclat
     */
    public void comprovarUsIdioma(String nomIdioma) throws IdiomaEnUs {
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            if (llista.getValue().getNomIdioma().equals(nomIdioma)) throw new IdiomaEnUs(nomIdioma);
        }
    }


    /**
     * Comprova que la llista identificada per nomLlista no està en ús en cap teclat
     * (de moment no comprova teclats d'altres usuaris perquè fa falta capa de persistencia)
     * @param nomLlista El nom de la llista
     * @throws LlistaFreqEnUs Si la llista està en ús per algun teclat
     */
    public void comprovarUsLlista(String nomLlista) throws LlistaFreqEnUs {
        for (Map.Entry<String, Teclat> llista : teclats.entrySet()) {
            if (llista.getValue().getNomLlistaFreq().equals(nomLlista)) throw new LlistaFreqEnUs(nomLlista);
        }
    }

    /**
     * Obté el teclat identificat per nomTeclat
     * @param nomTeclat El nom del teclat
     * @return El teclat identificat per nomTeclat
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public Teclat getTeclat(String nomTeclat) throws TeclatNoExisteix {
        comprovaTeclatNoExisteix(nomTeclat);
        return teclats.get(nomTeclat);
    }

}

//Classe Programada per: Agustí Costabella