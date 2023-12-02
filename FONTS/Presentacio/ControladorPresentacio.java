package Presentacio;

import java.util.*;

import ControladorsDomini.CtrlDomini;
import Excepcions.ExcepcionsCreadorTeclat;
import Presentacio.views.*;

public class ControladorPresentacio {
    private VistaTerminal vt;
    private static CtrlDomini controladorDomini;

    //Pre:
    //Post: S'obtenen els controladors de factoria, Domini i es crea i inicialitza una vista de terminal
    public ControladorPresentacio() {
        controladorDomini = controladorDomini.getInstance();
    }

    //Pre:
    //Post: S'inicia la presentació amb el perfil corresponent
    public static void iniciaInstancia(String nomPerfil) throws Exception {
        controladorDomini.iniciaInstancia(nomPerfil);
    }

    public static void vistaPerfils() throws Exception {
        VistaPerfils vp = new VistaPerfils();
    }

    public static void vistaCrearPerfil() throws Exception {
        VistaCrearPerfil vcp = new VistaCrearPerfil();
    }


    public static void vistaPrincipal() {
        VistaPrincipal vp = new VistaPrincipal();
    }

    public static void vistaInfoFuncions()  {
        VistaInfoFuncions vgt = new VistaInfoFuncions();
    }

    public static void vistaGestionarTeclats()  {
        VistaGestionarTeclats vgt = new VistaGestionarTeclats();
    }

    public static void vistaCrearTeclatLlistaPropia() { VistaCrearTeclatLlistaPropia vclp = new VistaCrearTeclatLlistaPropia(); }

    public static void vistaTeclat(char[][] teclat) {VistaTeclat vt = new VistaTeclat(teclat); }

    public static void vistaConsultarTeclats()  {
        VistaConsultarTeclats vct = new VistaConsultarTeclats();
    }

    public static void vistaGestionarDades() {
        VistaGestionarDades vgt = new VistaGestionarDades();
    }

    public static void vistaConsultarDades() {
        VistaConsultarDades vcd = new VistaConsultarDades();
    }


    //NORMAL
    public static void carregarDades()throws Exception{
        controladorDomini.carregarDadesSistema();
        controladorDomini.carregarDadesPerfil();
    }

    public void guardaEstat() {
        controladorDomini.guardaEstat();
    }

    public String getPerfilActual() {
        return controladorDomini.getPerfilActual();
    }

    public String getEstrategiaActual() {
        return controladorDomini.getEstrategiaActual();
    }

    public static List<String> getAllPerfils() {
        return controladorDomini.getAllPerfils();
    }


    //DADES
    public void novaLlistaPerfil(String tipusArxiu, String filepath, String idioma, Map<String,Integer> novesEntrades) throws Exception{
        controladorDomini.novaLlistaPerfil(tipusArxiu, filepath, idioma, novesEntrades);
    }

    public void modificarLlistaPerfil(String tipusArxiu, String nomArxiu, String nomllista, Map<String,Integer> novesEntrades) throws Exception {
        controladorDomini.modificarLlistaPerfil(tipusArxiu, nomArxiu, nomllista, novesEntrades);
    }

    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarLlista(nomLlista);
    }

    public static List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    public static String getNomIdiomaLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        return controladorDomini.getNomIdiomaLlista(nomLlista);
    }

    public Map<String, Integer> consultaLlista(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    public static Vector<String> consultaIdiomes() {
        return controladorDomini.consultaIdiomes();
    }

    public Vector<String> consultaAlfabets() {
        return controladorDomini.consultaAlfabets();
    }

    public void afegirAlfabet(String filepath) throws Exception{
        controladorDomini.afegirAlfabet(filepath);
    }

    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarAlfabet(nomAlfabet);
    }

    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filepath) throws Exception {
        controladorDomini.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filepath);
    }

    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminarIdioma(nomIdioma);
    }

    //TECLATS
    public List<String> getNomsTeclats() {
        return controladorDomini.getNomsTeclats();
    }

    public static List<String> getNomsIdiomes() {return controladorDomini.getNomsIdiomes();}

    public String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomIdiomaTeclat(nomt);
    }

    public String getNomLListaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomLListaTeclat(nomt);
    }

    public static char[][] consultaTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaTeclat(nomSeleccio);
    }

    public void modificarLayoutTeclat(String nomSeleccio, Integer numf, Integer numc) throws ExcepcionsCreadorTeclat {
        controladorDomini.modificarLayoutTeclat(nomSeleccio, numf, numc);
    }

    public static void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, Integer n, Integer m) throws ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m);
    }

    public void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, Integer n, Integer m) throws  ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m);
    }

    public void eliminarTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarTeclat(nomSeleccio);
    }

}

