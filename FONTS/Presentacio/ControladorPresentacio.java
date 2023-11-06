package Presentacio;
import java.io.FileNotFoundException;

import java.util.*;

import Domini.FactoriaController;
import ControladorsDomini.CtrlDomini;

public class ControladorPresentacio {
    private VistaTerminal vt;
    private CtrlDomini controladorDomini;

    //Pre:
    //Post: S'obtenen els controladors de factoria, Domini i es crea i inicialitza una vista de terminal
    public ControladorPresentacio() throws FileNotFoundException {
        FactoriaController fc = FactoriaController.getInstance(); //retorna el factoriaControler o es crea
        fc.CrearControladorDomini(); //Crea el controlador de domini
        controladorDomini = fc.getControladorDomini();
        vt = new VistaTerminal(this);
        iniciaInstancia();
        vt.inicialitzaTerminal();
    }

    //Pre:
    //Post: S'inicia una instancia amb el perfil x
    public void iniciaInstancia() {
        controladorDomini.iniciaInstancia("Marc");
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

    public void llegirLlistaFreq(String tipusArxiu, String filename)  {
       controladorDomini.llegirLlistaFreq(tipusArxiu,filename);
    }

    //Pre:
    //Post: S'obté un set dels noms de les llistes guardades del perfil actiu
    public List<String> getNomLlistesGuardades() {
        return controladorDomini.getNomLlistesGuardades();
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) {
        return controladorDomini.consultaLlista(nomSeleccio);
    }

    public void afegirAlfabet() {
        vt.mostrarMissatge("Afegir Alfabet");
        String nomalf = vt.obteNomAlfabet();
        Set<Character> lletres = vt.obteLletresAlfabet();

        int codierr = controladorDomini.afegirAlfabet(nomalf, lletres);

        switch (codierr) {
            case 0: vt.mostrarMissatge("Nou Alfabet afegit"); break;
            case 1: vt.mostrarMissatge("Alfabet ja existeix"); break;
            default: vt.mostrarMissatge("Error imposible " + codierr); break;
        }
    }

    public void consultaAlfabets() {
        vt.mostrarMissatge("Consulta d'Alfabets");
        Vector<String> dades = controladorDomini.consultaAlfabets();
        vt.mostrarDadesAlfabets(dades);
    }
}