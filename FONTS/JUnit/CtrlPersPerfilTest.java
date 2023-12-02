package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersPerfil;
import Domini.Perfil;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;
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
     * Comprova que el controlador de perfil només es crea un cop
     */
    @Test
    public void getControladorUnCopCreat() {
        CtrlPersPerfil cP;
        cP = CtrlPersPerfil.getInstance(cD);
        assertSame(cP, CtrlPersPerfil.getInstance(cD));
    }

    /**
     * Comprova que es carreguen els perfils
     */
    @Test
    public void carregarPerfils() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.carregarPerfil();
        try {
            cP.getPerfil("Prova");
            assertTrue(true);
        } catch (PerfilNoExisteix e) {
            fail();
        }
    }

    /**
     * Comprova que es pot afegir un perfil
     */
    @Test
    public void afegirPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.canviaPerfil("NouPerfil");
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }

    /**
     * Comprova que es pot obtenir un perfil
     */
    @Test
    public void getPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.canviaPerfil("NouPerfil");
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }

    /**
     * Comprova que no es pot obtenir un perfil inexistent
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
     */
    @Test
    public void getAllPerfils() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.canviaPerfil("SegonPerfil");
        cP.canviaPerfil("TercerPerfil");
        List<String> perfilsEsperats = new ArrayList<>();
        perfilsEsperats.add("NouPerfil");
        perfilsEsperats.add("SegonPerfil");
        perfilsEsperats.add("TercerPerfil");
        perfilsEsperats.add("Prova");
        assertTrue(cP.getAllPerfils().containsAll(perfilsEsperats));
    }

}

//Classe Programada per: Marc