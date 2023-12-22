package Presentacio;

import java.util.*;

import ControladorsDomini.CtrlDomini;
import Excepcions.*;
import Presentacio.views.*;
import javax.swing.JOptionPane;

/**
 * ControladorPresentacio es la classe que s'encarrega de comunicar les Vistes amb el Controlador de Domini
 */
public class ControladorPresentacio {
    /**
     * Controlador de Domini
     */
    private static CtrlDomini controladorDomini;

    /**
     * Creadora de ControladorPresentacio
     * <p> S'obté el controlador de Domini i es carrega la info del sistema </p>
     * @throws Exception si hi ha algun error en la inicialització
     */
    public ControladorPresentacio() throws Exception {
        controladorDomini = controladorDomini.getInstance();
        controladorDomini.carregaPerfils();
        controladorDomini.carregarDadesSistema();
    }

    /**
     * Mostra un missatge d'error amb missatge missatge
     * @param missatge El missatge a mostrar
     */
    public static void mostraError(String missatge) {
        JOptionPane.showMessageDialog(null, missatge, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mostra un missatge d'avis amb missatge missatge
     * @param missatge El missatge a mostrar
     */
    public static void mostraAvis(String missatge) {
        JOptionPane.showMessageDialog(null, missatge, "Avis", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Crida la funció getNomsIdiomes() del controlador de dominii
     * @return llista amb els noms dels idiomes del sistema
     */
    public static List<String> getNomsIdiomes() {return controladorDomini.getNomsIdiomes();}

    /**
     * Crida la funció creaPerfil(nomPerfil) del controlador de domini
     * @param nomPerfil nom del perfil a crear i amb el que s'inicia la instancia
     * @throws Exception si hi ha algun error en la inicialització
     */
    public static void creaPerfil(String nomPerfil) throws Exception{
        controladorDomini.crearPerfil(nomPerfil);
    }

    /**
     * Crida la funció canviarPerfil(nomPerfil) del controlador de domini
     * @param nomPerfil nom del perfil a canviar
     * @throws Exception si hi ha algun error en la inicialització
     */
    public static void canviarPerfil(String nomPerfil) throws Exception{
        controladorDomini.canviarPerfil(nomPerfil);
    }

    /**
     * Mostra en pantalla la vistaPerfils
     */
    public static void vistaPerfils() { VistaPerfils vp = new VistaPerfils();}
    /**
     * Mostra en pantalla la vistaCrearPerfil
     */
    public static void vistaCrearPerfil() { VistaCrearPerfil vcp = new VistaCrearPerfil();}
    /**
     * Mostra en pantalla la vistaPrincipal
     */
    public static void vistaPrincipal() {
        VistaPrincipal vp = new VistaPrincipal();
    }
    /**
     * Mostra en pantalla la vistaCrearTeclat
     */
    public static void vistaCrearTeclat() { VistaCrearTeclat vclp = new VistaCrearTeclat(); }

    /**
     * Mostra en pantalla la vistaTeclat
     * @param nom nom del teclat a mostrar
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nom no existeix o hi ha algun problema en carregar-lo
     */
    public static void vistaTeclat(String nom) throws ExcepcionsCreadorTeclat {VistaTeclat vt = new VistaTeclat(nom); }

    /**
     * Mostra en pantalla la vistaLlista
     * @param nomLl nom de la llista a mostrar
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLl no existeix
     */
    public static void vistaLlista(String nomLl) throws LlistaFreqNoExisteix { VistaLlista v = new VistaLlista(nomLl); }

    /**
     * Mostra en pantalla la vistaAfegirLlista
     */
    public static void vistaAfegirLlista() { VistaAfegirLlista v = new VistaAfegirLlista(); }

    /**
     * Mostra en pantalla la vistaIdioma
     * @param nomI nom de l'idioma a mostrar
     * @throws IdiomaNoExisteix Si l'idioma amb nom nomI no existeix
     */
    public static void vistaIdioma(String nomI) throws IdiomaNoExisteix { VistaIdioma v = new VistaIdioma(nomI); }

    /**
     * Mostra en pantalla la vistaAfegirIdioma
     */
    public static void vistaAfegirIdioma() { VistaAfegirIdioma v = new VistaAfegirIdioma(); }
    /**
     * Mostra en pantalla la vistaAfegirAlfabet
     */
    public static void vistaAfegirAlfabet() { VistaAfegirAlfabet v = new VistaAfegirAlfabet(); }
    /**
     * Mostra en pantalla la vistaAlfabet
     * @param nomA nom de l'alfabet a mostrar
     * @throws AlfabetNoExisteix Si l'alfabet amb nom nomA no existeix
     */
    public static void vistaAlfabet(String nomA) throws AlfabetNoExisteix { VistaAlfabet v = new VistaAlfabet(nomA); }

    /**
     * Mostra en pantalla la vistaElements
     * @param option El tipus d'element a mostrar
     */
    public static void vistaElements(String option) { VistaElements v = new VistaElements(option);}




    //NORMAL

    /**
     * Crida les funcions carregarDadesSistema() i carregarDadesPerfil() del controlador de domini
     * @throws Exception Si hi ha algun error en carregar les dades
     */
    public static void carregarDades()throws Exception{
        controladorDomini.carregarDadesSistema();
        controladorDomini.carregarDadesPerfil();
    }

    /**
     * Crida la funció guardaEstat() del controlador de domini
     */
    public static void guardaEstat() {
        controladorDomini.guardaEstat();
    }


    /**
     * Criada la funció getPerfilActual() del controlador de domini
     * @return nom del perfil actual
     */
    public static String getPerfilActual() {
        return controladorDomini.getPerfilActual();
    }

    /**
     * Crida la funció getAllPerfils() del controlador de domini
     * @return llista amb els noms d'usuari dels perfils
     */
    public static List<String> getAllPerfils() {
        return controladorDomini.getAllPerfils();
    }


    //DADES
    /**
     * Crida la funció novaLlistaPerfil(tipusArxiu, filepath, idioma, novesEntrades) del controlador de domini
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
     * Crida la funció modificarLlistaPerfil(tipusArxiu, pathArxiu, nomllista, novesEntrades) del controlador de domini
     * @param tipusArxiu El tipus d'arxiu
     * @param pathArxiu El path de l'arxiu
     * @param nomllista El nom de la llista de frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot modificar la llista de frequencies
     */
    public static void modificarLlistaPerfil(String tipusArxiu, String pathArxiu, String nomllista, Map<String,Integer> novesEntrades) throws Exception {
        controladorDomini.modificarLlistaPerfil(tipusArxiu, pathArxiu, nomllista, novesEntrades);
    }

    /**
     * Crida la funció eliminarLlista(nomLlista) del controlador de domini
     * @param nomLlista El nom de la llista de frequencies
     * @throws ExcepcionsCreadorTeclat Si la llista amb nom nomLlista no existeix o no es pot eliminar
     */
    public static void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarLlista(nomLlista);
    }

    /**
     * Crida la funció getNomLlistesGuardades() del controlador de domini
     * @return Els noms de les llistes de frequencies del Perfil Actual
     */
    public static List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    /**
     * Crida la funció getNomIdiomaLlista(nomLlista) del controlador de domini
     * @param nomLlista El nom de la llista de frequencies
     * @return El nom de l'idioma de la llista amb nom nomllista
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public static String getNomIdiomaLlista(String nomLlista) throws LlistaFreqNoExisteix{
        return controladorDomini.getNomIdiomaLlista(nomLlista);
    }

    /**
     * Crida la funció consultaLlista(nomSeleccio) del controlador de domini
     * @param nomSeleccio El nom de la llista de frequencies
     * @return El Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public static Map<String, Integer> consultaLlista(String nomSeleccio) throws LlistaFreqNoExisteix{
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    /**
     * Crida la funció consultaIdiomes() del controlador de domini
     * @return La info dels idiomes del sistema
     */
    public static Vector<String> consultaIdiomes() {
        return controladorDomini.consultaIdiomes();
    }

    /**
     * Crida la funció consultaAlfabets() del controlador de domini
     * @return La info dels alfabets guardats al sistema
     */
    public static Vector<String> consultaAlfabets() {
        return controladorDomini.consultaAlfabets();
    }

    /**
     * Crida la funció afegirAlfabet(filename) del controlador de domini
     * @param filename El nom de l'arxiu
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir l'alfabet
     */
    public static void afegirAlfabet(String filename) throws Exception{
        controladorDomini.afegirAlfabet(filename);
    }

    /**
     * Crida la funció eliminarAlfabet(nomAlfabet) del controlador de domini
     * @param nomAlfabet El nom de l'alfabet
     * @throws ExcepcionsCreadorTeclat Si l'alfabet amb nom nomAlfabet no existeix o no es pot eliminar
     */
    public static void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarAlfabet(nomAlfabet);
    }

    /**
     * Crida la funció afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename) del controlador de domini
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
     * Crida la funció eliminarIdioma(nomIdioma) del controlador de domini
     * @param nomIdioma El nom de l'idioma
     * @throws ExcepcionsCreadorTeclat Si l'idioma amb nom nomIdioma no existeix o no es pot eliminar
     */
    public static void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminarIdioma(nomIdioma);
    }

    //TECLATS
    /**
     * Crida la funció getNomsTeclats() del controlador de domini
     * @return Els noms dels teclats del Perfil Actual
     */
    public static List<String> getNomsTeclats() {
        return controladorDomini.getNomsTeclats();
    }

    /**
     * Crida la funció getNomIdiomaTeclat(nomt) del controlador de domini
     * @param nomt El nom del teclat
     * @return El nom de l'idioma del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public static String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomIdiomaTeclat(nomt);
    }

    /**
     * Crida la funció getNomLListaTeclat(nomt) del controlador de domini
     * @param nomt El nom del teclat
     * @return El nom de la llista de frequencies del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public static String getNomLListaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomLListaTeclat(nomt);
    }

    /**
     * Crida la funció consultaTeclat(nomTeclat) del controlador de domini
     * @param nomSeleccio El nom del teclat
     * @return La disposició de lletres del teclat amb nom nomTeclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix
     */
    public static char[][] consultaTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaTeclat(nomSeleccio);
    }

    /**
     * Crida la funció modificarLayoutTeclat(nomSeleccio, numf, numc) del controlador de domini
     * @param nomSeleccio El nom del teclat
     * @param numf El nombre de files
     * @param numc El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot modificar el teclat
     */
    public static void modificarLayoutTeclat(String nomSeleccio, Integer numf, Integer numc) throws ExcepcionsCreadorTeclat {
        controladorDomini.modificarLayoutTeclat(nomSeleccio, numf, numc);
    }

    /**
     * Crida la funció crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m, e) del controlador de domini
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param nomLlistaFreq El nom de la llista de frequencies
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @param e El nom de l'estrategia
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public static void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, Integer n, Integer m, String e) throws ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m, e);
    }

    /**
     * Crida la funció crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m, e) del controlador de domini
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @param e El nom de l'estrategia
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public static void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, Integer n, Integer m, String e) throws  ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m, e);
    }

    /**
     * Crida la funció eliminarTeclat(nomSeleccio) del controlador de domini
     * @param nomSeleccio El nom del teclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot eliminar
     */
    public static void eliminarTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarTeclat(nomSeleccio);
    }

    /**
     * Crida la funció consultaIdioma(nomI) del controlador de domini
     * @param nomI El nom de l'idioma
     * @return La info de l'idioma amb nom nomI
     * @throws IdiomaNoExisteix Si l'idioma amb nom nomI no existeix
     */
    public static String consultaIdioma(String nomI) throws IdiomaNoExisteix {
        return controladorDomini.consultaIdioma(nomI);
    }

    /**
     * Crida la funció consultaAlfabet(nomA) del controlador de domini
     * @param nomA El nom de l'alfabet
     * @return La info de l'alfabet amb nom nomA
     * @throws AlfabetNoExisteix Si l'alfabet amb nom nomA no existeix
     */
    public static String consultaAlfabet(String nomA) throws AlfabetNoExisteix {
        return controladorDomini.consultaAlfabet(nomA);
    }

    /**
     * Crida la funció getNomsAlfabets() del controlador de domini
     * @return Els noms dels alfabets del sistema
     */
    public static List<String> getNomsAlfabets()  {
        return controladorDomini.getNomsAlfabets();
    }

    /**
     * Crida la funció eliminaPerfil() del controlador de domini
     * @throws ExcepcionsCreadorTeclat Si no es pot eliminar el perfil
     */
    public static void eliminaPerfil() throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminaPerfil();
    }


}

