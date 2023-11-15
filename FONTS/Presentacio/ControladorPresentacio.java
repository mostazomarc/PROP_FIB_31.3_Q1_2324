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
    public ControladorPresentacio() throws FileNotFoundException {
        controladorDomini = controladorDomini.getInstance();
        vt = new VistaTerminal(this);
        iniciaInstancia();
        vt.inicialitzaTerminal();
    }

    //Pre:
    //Post: S'inicia una instancia amb el perfil x
    public void iniciaInstancia() {
        controladorDomini.iniciaInstancia("Prova");
    }

    //Pre:
    //Post: S'obte l'estrategia actuall del sistema
    public String getEstrategiaActual() {
        return controladorDomini.getEstrategiaActual();
    }

    //Pre: Ja s'ha iniciat una instancia amb un perfil
    //Post: S'obte l'usuari actiu actual del sistema
    public String getPerfilActual() {
        return controladorDomini.getPerfilActual();
    }

    public void llegirLlistaFreq(String tipusArxiu, String filename, String idioma)  {
        Map<String, Integer> novesEntrades = new HashMap<>();
       controladorDomini.novaLlistaPerfil(tipusArxiu,filename,idioma,novesEntrades);
    }

    public void afegirAlfabet(String filename) {
        controladorDomini.afegirAlfabet(filename);
    }

    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filename) {
        controladorDomini.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
    }

    //Pre:
    //Post: S'obté un set dels noms de les llistes guardades del perfil actiu
    public List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    public void consultaIdiomes() {
        vt.mostrarMissatge("### Consultar Idiomes ###");
        Vector<String> dades = controladorDomini.consultaIdiomes();
        vt.mostraDadesIdiomes(dades);
    }
}