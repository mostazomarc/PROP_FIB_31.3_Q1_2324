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
        iniciaInstancia();
        vt.inicialitzaTerminal();
    }

    //Pre:
    //Post: S'inicia una instancia amb el perfil x
    public void iniciaInstancia() throws Exception{
        controladorDomini.iniciaInstancia("Prova");
    }
}

