package presentacio;
import java.io.FileNotFoundException;

import ControladorsDomini.CtrlDomini;
import Domini.FactoriaController;

public class ControladorPresentacio {
    private VistaTerminal vt;
    private CtrlDomini controladorDomini;

    public ControladorPresentacio() throws FileNotFoundException {
        FactoriaController fc = FactoriaController.getInstance();

    }
}