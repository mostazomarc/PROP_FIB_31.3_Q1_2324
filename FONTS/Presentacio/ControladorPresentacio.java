package Presentacio;
import java.io.FileNotFoundException;

import java.util.*;

import ControladorsDomini.CtrlDomini;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LlistaFreqNoExisteix;
import Excepcions.TeclatNoExisteix;

/**
 * ControladorPresentacio es la classe que s'encarrega de comunicar la Vista amb el Controlador de Domini
 */
public class ControladorPresentacio {
    /**
     * Vista de terminal
     */
    private VistaTerminal vt;
    /**
     * Controlador de Domini
     */
    private CtrlDomini controladorDomini;

    /**
     * Creadora de ControladorPresentacio
     * <p> S'obté el controlador de Domini i es crea i inicialitza una vista de terminal </p>
     * @throws Exception si hi ha algun error en la inicialització
     */
    public ControladorPresentacio() throws Exception {
        controladorDomini = controladorDomini.getInstance();
        vt = new VistaTerminal(this);
        vt.inicialitzaTerminal();
    }

    /**
     * Inicia una instancia amb el perfil x
     * @param nomPerfil nom del perfil amb el que s'inicia la instancia
     * @throws Exception si hi ha algun error en la inicialització
     */
    public void iniciaInstancia(String nomPerfil) throws Exception{
        controladorDomini.iniciaInstancia(nomPerfil);
    }

    //NORMAL

    /**
     * Carrega les dades del sistema i del perfil
     * @throws Exception Si hi ha algun error en carregar les dades
     */
    public void carregarDades()throws Exception{
        controladorDomini.carregarDadesSistema();
        controladorDomini.carregarDadesPerfil();
    }

    /**
     * Guarda les dades del sistema i del perfil
     */
    public void guardaEstat() {
        controladorDomini.guardaEstat();
    }

    /**
     * Obté el nom del perfil actual
     * @return nom del perfil actual
     */
    public String getPerfilActual() {
        return controladorDomini.getPerfilActual();
    }

    /**
     * Obté el nom de l'estratègia actual
     * @return nom de l'estratègia actual
     */
    public String getEstrategiaActual() {
        return controladorDomini.getEstrategiaActual();
    }

    /**
     * Obté una llista amb els noms d'usuari dels perfils
     * @return llista amb els noms d'usuari dels perfils
     */
    public List<String> getAllPerfils() {
        return controladorDomini.getAllPerfils();
    }


    //DADES
    /**
     * Afegeix la informació de l'arxiu de llista de frequencies filename al Perfil Actual com una nova llista de frequencies
     * @param tipusArxiu El tipus d'arxiu
     * @param filename El nom de l'arxiu
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir la llista de frequencies
     */
    public void novaLlistaPerfil(String tipusArxiu, String filename, String idioma, Map<String,Integer> novesEntrades) throws Exception{
        controladorDomini.novaLlistaPerfil(tipusArxiu, filename, idioma, novesEntrades);
    }

    /**
     * Modifica l'indormació de la llista de frequencies identificada per nomLlista del Perfil Actual amb les noves dades entrades
     * @param tipusArxiu El tipus d'arxiu
     * @param nomArxiu El nom de l'arxiu
     * @param nomllista El nom de la llista de frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot modificar la llista de frequencies
     */
    public void modificarLlistaPerfil(String tipusArxiu, String nomArxiu, String nomllista, Map<String,Integer> novesEntrades) throws Exception {
        controladorDomini.modificarLlistaPerfil(tipusArxiu, nomArxiu, nomllista, novesEntrades);
    }

    /**
     * Elimina la llista de frequencies amb nom nomLlista del Perfil Actual
     * @param nomLlista El nom de la llista de frequencies
     * @throws ExcepcionsCreadorTeclat Si la llista amb nom nomLlista no existeix o no es pot eliminar
     */
    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarLlista(nomLlista);
    }

    /**
     * Retorna els noms de les llistes de frequencies del Perfil Actual
     * @return Els noms de les llistes de frequencies del Perfil Actual
     */
    public List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    /**
     * Retorna el nom de l'idioma de la llista amb nom nomllista
     * @param nomLlista El nom de la llista de frequencies
     * @return El nom de l'idioma de la llista amb nom nomllista
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public String getNomIdiomaLlista(String nomLlista) throws LlistaFreqNoExisteix{
        return controladorDomini.getNomIdiomaLlista(nomLlista);
    }

    /**
     * Retorna el Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @param nomSeleccio El nom de la llista de frequencies
     * @return El Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws LlistaFreqNoExisteix{
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    /**
     * Retorna la info dels idiomes del sistema
     * @return La info dels idiomes del sistema
     */
    public Vector<String> consultaIdiomes() {
        return controladorDomini.consultaIdiomes();
    }

    /**
     * Consulta la info dels alfabets guardats al sistema
     * @return La info dels alfabets guardats al sistema
     */
    public Vector<String> consultaAlfabets() {
        return controladorDomini.consultaAlfabets();
    }

    /**
     * Afegeix l'alfabet de l'arxiu filename al sistema
     * @param filename El nom de l'arxiu
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir l'alfabet
     */
    public void afegirAlfabet(String filename) throws Exception{
        controladorDomini.afegirAlfabet(filename);
    }

    /**
     * Elimina l'alfabet amb nom nomAlfabet del sistema
     * @param nomAlfabet El nom de l'alfabet
     * @throws ExcepcionsCreadorTeclat Si l'alfabet amb nom nomAlfabet no existeix o no es pot eliminar
     */
    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarAlfabet(nomAlfabet);
    }

    /**
     * Afegeix l'idioma amb nom nomIdioma, alfabet amb nom nomAlfabet i llista de frequencies creada amb les dades entrades
     * @param nomIdioma El nom de l'idioma
     * @param nomAlfabet El nom de l'alfabet
     * @param tipusArxiu El tipus d'arxiu
     * @param filename El nom de l'arxiu
     * @throws Exception Si l'idioma ja existeix o no es pot afegir l'idioma o la llista de frequencies
     */
    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filename) throws Exception {
        controladorDomini.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
    }

    /**
     * Elimina l'idioma amb nom nomIdioma del sistema
     * @param nomIdioma El nom de l'idioma
     * @throws ExcepcionsCreadorTeclat Si l'idioma amb nom nomIdioma no existeix o no es pot eliminar
     */
    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminarIdioma(nomIdioma);
    }

    //TECLATS
    /**
     * Retorna els noms dels teclats del Perfil Actual
     * @return Els noms dels teclats del Perfil Actual
     */
    public List<String> getNomsTeclats() {
        return controladorDomini.getNomsTeclats();
    }

    /**
     * Retorna el nom de l'idioma del teclat identificat per nomt
     * @param nomt El nom del teclat
     * @return El nom de l'idioma del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomIdiomaTeclat(nomt);
    }

    /**
     * Retorna el nom de la llista de frequencies del teclat identificat per nomt
     * @param nomt El nom del teclat
     * @return El nom de la llista de frequencies del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getNomLListaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomLListaTeclat(nomt);
    }

    /**
     * Retorna la disposició de lletres del teclat amb nom nomTeclat
     * @param nomSeleccio El nom del teclat
     * @return La disposició de lletres del teclat amb nom nomTeclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix
     */
    public char[][] consultaTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaTeclat(nomSeleccio);
    }

    /**
     * Modifica el layout del teclat identificat per nomTeclat amb n files i m columnes
     * @param nomSeleccio El nom del teclat
     * @param numf El nombre de files
     * @param numc El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot modificar el teclat
     */
    public void modificarLayoutTeclat(String nomSeleccio, Integer numf, Integer numc) throws ExcepcionsCreadorTeclat {
        controladorDomini.modificarLayoutTeclat(nomSeleccio, numf, numc);
    }

    /**
     * Afegeix i crea un teclat amb nom nomTeclat, idioma amb nom nomIdioma, llista de frequencies amb nom nomLlistaFreq, n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param nomLlistaFreq El nom de la llista de frequencies
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, Integer n, Integer m) throws ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m);
    }

    /**
     * Afegeix i crea un teclat amb nom nomTeclat, idioma amb nom nomIdioma, la llista de l'idioma, n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, Integer n, Integer m) throws  ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m);
    }

    /**
     * Elimina el teclat amb nom nomTeclat del Perfil Actual
     * @param nomSeleccio El nom del teclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot eliminar
     */
    public void eliminarTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarTeclat(nomSeleccio);
    }

}

