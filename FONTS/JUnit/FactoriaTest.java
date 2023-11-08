package JUnit;

import ControladorsDomini.CtrlDomini;
import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import java.util.*;

public class FactoriaTest {

    @Test
    //getInstance
    public void getInstance() {
        FactoriaController factoria = FactoriaController.getInstance();
        assertNotNull(factoria);
    }
    @Test
    //CrearControladorDomini
    public void CrearControladorDomini() {
        FactoriaController factoria = FactoriaController.getInstance();
        factoria.CrearControladorDomini();
        CtrlDomini resultat = factoria.getControladorDomini();
        assertNotNull(resultat);
    }
    @Test
    //getControladorDomini
    public void getControladorDomini() {
        FactoriaController factoria = FactoriaController.getInstance();
        factoria.CrearControladorDomini();
        CtrlDomini resultat = factoria.getControladorDomini();
        assertNotNull(resultat);
    }
}