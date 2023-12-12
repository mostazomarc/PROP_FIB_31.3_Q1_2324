package Presentacio;

import java.util.*;

import ControladorsDomini.CtrlDomini;
import Excepcions.*;
import Presentacio.views.*;
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
    private static CtrlDomini controladorDomini;

    /**
     * Creadora de ControladorPresentacio
     * <p> S'obté el controlador de Domini i es crea i inicialitza una vista de terminal </p>
     * @throws Exception si hi ha algun error en la inicialització
     */
    public ControladorPresentacio() throws Exception {
        controladorDomini = controladorDomini.getInstance();
        controladorDomini.carregaPerfils();
    }

    public static List<String> getNomsIdiomes() {return controladorDomini.getNomsIdiomes();}

    /**
     * Inicia una instancia amb el perfil x
     * @param nomPerfil nom del perfil amb el que s'inicia la instancia
     * @throws Exception si hi ha algun error en la inicialització
     */
    public static void iniciaInstancia(String nomPerfil) throws Exception{
        controladorDomini.iniciaInstancia(nomPerfil);
    }

    public static void vistaPerfils() throws Exception { VistaPerfils vp = new VistaPerfils();}
    public static void vistaCrearPerfil() throws Exception { VistaCrearPerfil vcp = new VistaCrearPerfil();}
    public static void vistaPrincipal() {
        VistaPrincipal vp = new VistaPrincipal();
    }
    public static void vistaCrearTeclat() { VistaCrearTeclat vclp = new VistaCrearTeclat(); }
    public static void vistaTeclat(String nom) throws ExcepcionsCreadorTeclat {VistaTeclat vt = new VistaTeclat(nom); }
    public static void vistaLlista(String nomLl) throws LlistaFreqNoExisteix { VistaLlista v = new VistaLlista(nomLl); }
    public static void vistaAfegirLlista() { VistaAfegirLlista v = new VistaAfegirLlista(); }
    public static void vistaIdioma(String nomI) throws IdiomaNoExisteix { VistaIdioma v = new VistaIdioma(nomI); }
    public static void vistaAfegirIdioma() { VistaAfegirIdioma v = new VistaAfegirIdioma(); }
    public static void vistaAfegirAlfabet() { VistaAfegirAlfabet v = new VistaAfegirAlfabet(); }
    public static void vistaAlfabet(String nomA) throws AlfabetNoExisteix { VistaAlfabet v = new VistaAlfabet(nomA); }
    public static void vistaElements(String option) { VistaElements v = new VistaElements(option);}




    //NORMAL

    /**
     * Carrega les dades del sistema i del perfil
     * @throws Exception Si hi ha algun error en carregar les dades
     */
    public static void carregarDades()throws Exception{
        controladorDomini.carregarDadesSistema();
        controladorDomini.carregarDadesPerfil();
    }

    /**
     * Guarda les dades del sistema i del perfil
     */
    public static void guardaEstat() {
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
    public static List<String> getAllPerfils() {
        return controladorDomini.getAllPerfils();
    }


    //DADES
    /**
     * Afegeix la informació de l'arxiu de llista de frequencies filename al Perfil Actual com una nova llista de frequencies
     * @param tipusArxiu El tipus d'arxiu
     * @param filepath El path de l'arxiu
     * @param idioma El nom de l'idioma
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir la llista de frequencies
     */
    public static void novaLlistaPerfil(String tipusArxiu, String filepath, String idioma, Map<String, Integer> novesEntrades) throws Exception{
        controladorDomini.novaLlistaPerfil(tipusArxiu, filepath, idioma, novesEntrades);
    }

    /**
     * Modifica l'indormació de la llista de frequencies identificada per nomLlista del Perfil Actual amb les noves dades entrades
     * @param tipusArxiu El tipus d'arxiu
     * @param nomArxiu El nom de l'arxiu
     * @param nomllista El nom de la llista de frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot modificar la llista de frequencies
     */
    public static void modificarLlistaPerfil(String tipusArxiu, String nomArxiu, String nomllista, Map<String,Integer> novesEntrades) throws Exception {
        controladorDomini.modificarLlistaPerfil(tipusArxiu, nomArxiu, nomllista, novesEntrades);
    }

    /**
     * Elimina la llista de frequencies amb nom nomLlista del Perfil Actual
     * @param nomLlista El nom de la llista de frequencies
     * @throws ExcepcionsCreadorTeclat Si la llista amb nom nomLlista no existeix o no es pot eliminar
     */
    public static void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarLlista(nomLlista);
    }

    /**
     * Retorna els noms de les llistes de frequencies del Perfil Actual
     * @return Els noms de les llistes de frequencies del Perfil Actual
     */
    public static List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    /**
     * Retorna el nom de l'idioma de la llista amb nom nomllista
     * @param nomLlista El nom de la llista de frequencies
     * @return El nom de l'idioma de la llista amb nom nomllista
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public static String getNomIdiomaLlista(String nomLlista) throws LlistaFreqNoExisteix{
        return controladorDomini.getNomIdiomaLlista(nomLlista);
    }

    /**
     * Retorna el Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @param nomSeleccio El nom de la llista de frequencies
     * @return El Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public static Map<String, Integer> consultaLlista(String nomSeleccio) throws LlistaFreqNoExisteix{
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    /**
     * Retorna la info dels idiomes del sistema
     * @return La info dels idiomes del sistema
     */
    public static Vector<String> consultaIdiomes() {
        return controladorDomini.consultaIdiomes();
    }

    /**
     * Consulta la info dels alfabets guardats al sistema
     * @return La info dels alfabets guardats al sistema
     */
    public static Vector<String> consultaAlfabets() {
        return controladorDomini.consultaAlfabets();
    }

    /**
     * Afegeix l'alfabet de l'arxiu filename al sistema
     * @param filename El nom de l'arxiu
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir l'alfabet
     */
    public static void afegirAlfabet(String filename) throws Exception{
        controladorDomini.afegirAlfabet(filename);
    }

    /**
     * Elimina l'alfabet amb nom nomAlfabet del sistema
     * @param nomAlfabet El nom de l'alfabet
     * @throws ExcepcionsCreadorTeclat Si l'alfabet amb nom nomAlfabet no existeix o no es pot eliminar
     */
    public static void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat{
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
    public static void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filename) throws Exception {
        controladorDomini.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
    }

    /**
     * Elimina l'idioma amb nom nomIdioma del sistema
     * @param nomIdioma El nom de l'idioma
     * @throws ExcepcionsCreadorTeclat Si l'idioma amb nom nomIdioma no existeix o no es pot eliminar
     */
    public static void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminarIdioma(nomIdioma);
    }

    //TECLATS
    /**
     * Retorna els noms dels teclats del Perfil Actual
     * @return Els noms dels teclats del Perfil Actual
     */
    public static List<String> getNomsTeclats() {
        return controladorDomini.getNomsTeclats();
    }

    /**
     * Retorna el nom de l'idioma del teclat identificat per nomt
     * @param nomt El nom del teclat
     * @return El nom de l'idioma del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public static String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
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
    public static char[][] consultaTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaTeclat(nomSeleccio);
    }

    /**
     * Modifica el layout del teclat identificat per nomTeclat amb n files i m columnes
     * @param nomSeleccio El nom del teclat
     * @param numf El nombre de files
     * @param numc El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot modificar el teclat
     */
    public static void modificarLayoutTeclat(String nomSeleccio, Integer numf, Integer numc) throws ExcepcionsCreadorTeclat {
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
    public static void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, Integer n, Integer m, String e) throws ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m, e);
    }

    /**
     * Afegeix i crea un teclat amb nom nomTeclat, idioma amb nom nomIdioma, la llista de l'idioma, n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public static void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, Integer n, Integer m, String e) throws  ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m, e);
    }

    /**
     * Elimina el teclat amb nom nomTeclat del Perfil Actual
     * @param nomSeleccio El nom del teclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot eliminar
     */
    public static void eliminarTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarTeclat(nomSeleccio);
    }

    public static String consultaIdioma(String nomI) throws IdiomaNoExisteix {
        return controladorDomini.consultaIdioma(nomI);
    }

    public static String consultaAlfabet(String nomA) throws AlfabetNoExisteix {
        return controladorDomini.consultaAlfabet(nomA);
    }
    public static List<String> getNomsAlfabets()  {
        return controladorDomini.getNomsAlfabets();
    }

    public static void eliminaPerfil() throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminaPerfil();
    }

}

