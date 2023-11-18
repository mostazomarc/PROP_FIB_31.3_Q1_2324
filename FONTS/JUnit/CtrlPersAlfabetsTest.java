package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersAlfabets;
import Domini.Alfabet;
import Excepcions.AlfabetJaExisteix;
import Excepcions.AlfabetNoExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CtrlPersAlfabetsTest {
    CtrlPersAlfabets cP;

    CtrlDomini cD;

    List<String> LlistaLlegidaProva;

    Set<Character> lletresProva;

    @Before
    public void omplirLlistaLlegidaiLletres() {
        LlistaLlegidaProva = new ArrayList<>();
        LlistaLlegidaProva.add("abcd");
        lletresProva = new HashSet<>();
        lletresProva.add('a'); lletresProva.add('b'); lletresProva.add('c'); lletresProva.add('d');
    }

    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    @Before
    public void getControladorPersistencia() {
        cP = CtrlPersAlfabets.getInstance(cD);
    }

    @After
    public void eliminarAlfabetProva() throws Exception {
        try {
            cP.eliminarAlfabet("AlfabetProva");
        } catch (Exception e) {
        }
    }

    //S'eliminen els alfabets creats per poderr tornar-los a crear
    @Test
    public void getControladorUnCopCreat() {
        assertSame(cP, CtrlPersAlfabets.getInstance(cD));
    }

    @Test
    public void afegirAlfabet() throws Exception {
        cP.afegirAlfabet("AlfabetProva.txt", LlistaLlegidaProva);
        Alfabet resultat = cP.getAlfabet("AlfabetProva");
        assertEquals("AlfabetProva", resultat.getNomAlfabet());
        assertEquals(lletresProva, resultat.getLletres());
    }

    @Test
    public void eliminarAlfabet() throws Exception {
        cP.afegirAlfabet("AlfabetProva.txt", LlistaLlegidaProva);
        cP.eliminarAlfabet("AlfabetProva");
        try {
            cP.getAlfabet("AlfabetProva");
        } catch (AlfabetNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void afegirAlfabetDuplicat() throws Exception {
        cP.afegirAlfabet("AlfabetProva.txt", LlistaLlegidaProva);
        try {
            cP.afegirAlfabet("AlfabetProva.txt", LlistaLlegidaProva);
            fail();
        } catch (AlfabetJaExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void eliminarAlfabetInexistent() throws Exception {
        try {
            cP.eliminarAlfabet("AlfabetProva");
            fail();
        } catch (AlfabetNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void getAlfabet() throws Exception {
        cP.afegirAlfabet("AlfabetProva.txt", LlistaLlegidaProva);
        Alfabet resultat = cP.getAlfabet("AlfabetProva");
        assertEquals("AlfabetProva", resultat.getNomAlfabet());
        assertEquals(lletresProva, resultat.getLletres());
    }

}

//Classe Programada per: Arnau Tajahuerce
