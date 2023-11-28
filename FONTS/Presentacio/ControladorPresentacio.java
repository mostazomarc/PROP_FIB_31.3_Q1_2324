package Presentacio;
import java.io.FileNotFoundException;

import java.util.*;

import ControladorsDomini.CtrlDomini;
import Excepcions.ExcepcionsCreadorTeclat;

public class ControladorPresentacio {
    private VistaTerminal vt;
    private CtrlDomini controladorDomini;

    //Pre:
    //Post: S'obtenen els controladors de factoria, Domini i es crea i inicialitza una vista de terminal
    public ControladorPresentacio() throws Exception {
        controladorDomini = controladorDomini.getInstance();
        vt = new VistaTerminal(this);
        vt.inicialitzaTerminal();
    }

    //Pre:
    //Post: S'inicia una instancia amb el perfil x
    public void iniciaInstancia(String nomPerfil) throws Exception{
        controladorDomini.iniciaInstancia(nomPerfil);
    }

    //NORMAL
    public void carregarDades()throws Exception{
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

    public List<String> getAllPerfils() {
        return controladorDomini.getAllPerfils();
    }


    //DADES
    public void novaLlistaPerfil(String tipusArxiu, String filename, String idioma, Map<String,Integer> novesEntrades) throws Exception{
        controladorDomini.novaLlistaPerfil(tipusArxiu, filename, idioma, novesEntrades);
    }

    public void modificarLlistaPerfil(String tipusArxiu, String nomArxiu, String nomllista, Map<String,Integer> novesEntrades) throws Exception {
        controladorDomini.modificarLlistaPerfil(tipusArxiu, nomArxiu, nomllista, novesEntrades);
    }

    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarLlista(nomLlista);
    }

    public List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    public String getNomIdiomaLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        return controladorDomini.getNomIdiomaLlista(nomLlista);
    }

    public Map<String, Integer> consultaLlista(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    public Vector<String> consultaIdiomes() {
        return controladorDomini.consultaIdiomes();
    }

    public Vector<String> consultaAlfabets() {
        return controladorDomini.consultaAlfabets();
    }

    public void afegirAlfabet(String filename) throws Exception{
        controladorDomini.afegirAlfabet(filename);
    }

    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarAlfabet(nomAlfabet);
    }

    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filename) throws Exception {
        controladorDomini.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
    }

    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        controladorDomini.eliminarIdioma(nomIdioma);
    }

    //TECLATS
    public List<String> getNomsTeclats() {
        return controladorDomini.getNomsTeclats();
    }

    public String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomIdiomaTeclat(nomt);
    }

    public String getNomLListaTeclat(String nomt) throws ExcepcionsCreadorTeclat {
        return controladorDomini.getNomLListaTeclat(nomt);
    }

    public char[][] consultaTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaTeclat(nomSeleccio);
    }

    public void modificarLayoutTeclat(String nomSeleccio, Integer numf, Integer numc) throws ExcepcionsCreadorTeclat {
        controladorDomini.modificarLayoutTeclat(nomSeleccio, numf, numc);
    }

    public void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, Integer n, Integer m) throws ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m);
    }

    public void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, Integer n, Integer m) throws  ExcepcionsCreadorTeclat {
        controladorDomini.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m);
    }

    public void eliminarTeclat(String nomSeleccio) throws ExcepcionsCreadorTeclat{
        controladorDomini.eliminarTeclat(nomSeleccio);
    }

}

