package Presentacio;
import java.io.FileNotFoundException;

import ControladorsDomini.CtrlDomini;
import Domini.FactoriaController;

public class ControladorPresentacio {
    private VistaTerminal vt;
    private CtrlDomini controladorDomini;

    public ControladorPresentacio() throws FileNotFoundException {
        FactoriaController fc = FactoriaController.getInstance(); //retorna el factoriaControler o es crea
        fc.CrearControladorDomini(); //Crea el controlador de domini
        controladorDomini = fc.getControladorDomini();
        vt = new VistaTerminal(this);
        iniciaInstancia();
        vt.inicialitzaTerminal();
    }

    public void iniciaInstancia() {
        System.out.println("\n nova instancia controlador domini \n");
    }
}