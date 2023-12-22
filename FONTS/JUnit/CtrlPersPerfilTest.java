package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersPerfil;
import Domini.Perfil;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CtrlPersPerfilTest es una classe que ens permet testejar les funcions de la classe CtrlPersPerfil
 * <p>
 * Les funcions que testeja són:
 * <ul>
 * <li>getControladorUnCopCreat</li>
 * <li>carregarPerfils</li>
 * <li>afegirPerfil</li>
 * <li>afegirPerfilDuplicat</li>
 * <li>getPerfil</li>
 * <li>getPerfilInexistent</li>
 * <li>getAllPerfils</li>
 * </ul>
 *
 * @see CtrlPersPerfil
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersPerfilTest {
    /**
     * Instancia de CtrlDomini
     */
    CtrlDomini cD;

    /**
     * Fa un get controlador de domini
     */
    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    /**
     * Elimina els perfils usats
     */
    @After
    public void eliminaPerfils() {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        try {
            cP.eliminaPerfil("SegonPerfil");
        } catch (PerfilNoExisteix perfilNoExisteix) {
            //No cal fer res
        }
        try {
            cP.eliminaPerfil("TercerPerfil");
        } catch (PerfilNoExisteix perfilNoExisteix) {
            //No cal fer res
        }
        try {
            cP.eliminaPerfil("SegonPerfil");
        } catch (PerfilNoExisteix perfilNoExisteix) {
            //No cal fer res
        }
        try {
            cP.eliminaPerfil("NouPerfil");
        } catch (PerfilNoExisteix perfilNoExisteix) {
            //No cal fer res
        }
        try {
            cP.eliminaPerfil("Prova");
        } catch (PerfilNoExisteix perfilNoExisteix) {
            //No cal fer res
        }
    }

    /**
     * Comprova que el controlador de perfil només es crea un cop
     */
    @Test
    public void getControladorUnCopCreat() {
        CtrlPersPerfil cP;
        cP = CtrlPersPerfil.getInstance(cD);
        assertSame(cP, CtrlPersPerfil.getInstance(cD));
    }

//    /**
//     * Comprova que es carreguen els perfils
//     */
//    @Test
//    public void carregarPerfils() throws Exception {
//        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
//        cP.carregarPerfil();
//        try {
//            cP.getPerfil("Prova");
//            assertTrue(true);
//        } catch (PerfilNoExisteix e) {
//            fail();
//        }
//    }

    /**
     * Comprova que es pot afegir un perfil
     * @throws Exception Si salta una excepció
     */
    @Test
    public void afegirPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("NouPerfil");
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }

    /**
     * Comprova que es pot obtenir un perfil
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("NouPerfil");
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }

    /**
     * Comprova que no es pot obtenir un perfil inexistent
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getPerfilInexistent() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        try {
            Perfil perfil = cP.getPerfil("Inexistent");
            assertTrue(false);
        } catch (PerfilNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Comprova que es poden obtenir tots els perfils
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getAllPerfils() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("SegonPerfil");
        cP.afegirPerfil("TercerPerfil");
        List<String> perfilsEsperats = new ArrayList<>();
        perfilsEsperats.add("SegonPerfil");
        perfilsEsperats.add("TercerPerfil");
        assertTrue(cP.getAllPerfils().containsAll(perfilsEsperats));
    }

    /**
     * Comprova que es pot canviar de perfil
     * @throws Exception Si salta una excepció
     */
    @Test
    public void canviarPerfils() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("SegonPerfil");
        cP.afegirPerfil("TercerPerfil");
        cP.canviaPerfil("SegonPerfil");
        assertTrue("SegonPerfil".equals(cP.getPerfilActual().getUsuari()));
    }

    /**
     * Comprova que no es pot crear un perfil duplicat
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test(expected=PerfilJaExisteix.class)
    public void perfilDuplicat() throws ExcepcionsCreadorTeclat {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("Prova");
        cP.afegirPerfil("Prova");
    }

}

//Classe Programada per: Marc