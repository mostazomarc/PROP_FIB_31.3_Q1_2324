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

public class CtrlPersPerfilTest {

    CtrlDomini cD;

    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }


    @Test
    public void getControladorUnCopCreat() {
        CtrlPersPerfil cP;
        cP = CtrlPersPerfil.getInstance(cD);
        assertSame(cP, CtrlPersPerfil.getInstance(cD));
    }

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

    @Test
    public void afegirPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("NouPerfil");
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }


    @Test
    public void afegirPerfilDuplicat() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("PerfilDuplicat");
        try {
            cP.afegirPerfil("PerfilDuplicat");
            assertTrue(false);
        } catch (PerfilJaExisteix e) {
            assertTrue(true);
        }
    }


    @Test
    public void getPerfil() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        Perfil perfil = cP.getPerfil("NouPerfil");
        assertEquals(perfil.getUsuari(), "NouPerfil");
    }

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

    @Test
    public void getAllPerfils() throws Exception {
        CtrlPersPerfil cP = CtrlPersPerfil.getInstance(cD);
        cP.afegirPerfil("SegonPerfil");
        cP.afegirPerfil("TercerPerfil");
        List<String> perfilsEsperats = new ArrayList<>();
        perfilsEsperats.add("NouPerfil");
        perfilsEsperats.add("SegonPerfil");
        perfilsEsperats.add("TercerPerfil");
        perfilsEsperats.add("Prova");
        assertTrue(cP.getAllPerfils().containsAll(perfilsEsperats));
    }

}

//Classe Programada per: Marc