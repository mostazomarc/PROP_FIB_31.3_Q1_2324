package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersIdiomes;
import Domini.Alfabet;
import Domini.Idioma;
import Excepcions.IdiomaJaExisteix;
import Excepcions.IdiomaNoExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class CtrlPersIdiomesTest {
    CtrlPersIdiomes cP;

    CtrlDomini cD;

    Alfabet alfabetProva;

    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
    private final Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        alfabetProva = new Alfabet("Prova", lletresProva);
    }

    @Before
    public void omplirLlistaProva() {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
    }

    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    @Before
    public void getControladorPersistencia() {
        cP = CtrlPersIdiomes.getInstance(cD);
    }

    @After
    public void eliminarIdiomaProva() throws Exception {
        try {
            cP.eliminarIdioma("IdiomaProva");
        } catch (Exception e) {
        }
    }

    @Test
    public void getControladorUnCopCreat() {
        assertSame(cP, CtrlPersIdiomes.getInstance(cD));
    }

    @Test
    public void afegirIdioma() throws Exception {
        cP.afegirIdioma("IdiomaProva", alfabetProva, llistaParaulesProva);
        Idioma resultat = cP.getIdioma("IdiomaProva");
        assertEquals("IdiomaProva", resultat.getNom());
        assertEquals(alfabetProva, resultat.getAlfabet());
        assertEquals(llistaParaulesProva, resultat.getFrequencies());
        assertEquals("LlistaPredIdiomaProva", resultat.getLlistaFreq().getNom());
    }

    @Test
    public void eliminarIdioma() throws Exception {
        cP.afegirIdioma("IdiomaProva", alfabetProva, llistaParaulesProva);
        cP.eliminarIdioma("IdiomaProva");
        try {
            cP.getIdioma("IdiomaProva");
            fail();
        } catch (IdiomaNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void afegirIdiomaDuplicat() throws Exception {
        cP.afegirIdioma("IdiomaProva", alfabetProva, llistaParaulesProva);
        try {
            cP.afegirIdioma("IdiomaProva", alfabetProva, llistaParaulesProva);
            fail();
        }
        catch (IdiomaJaExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void eliminarIdiomaInexistent() throws Exception {
        try {
            cP.eliminarIdioma("IdiomaProva");
            fail();
        } catch (IdiomaNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void getIdioma() throws Exception {
        cP.afegirIdioma("IdiomaProva", alfabetProva, llistaParaulesProva);
        Idioma resultat = cP.getIdioma("IdiomaProva");
        assertEquals("IdiomaProva", resultat.getNom());
        assertEquals("LlistaPredIdiomaProva", resultat.getLlistaFreq().getNom());
        assertEquals(llistaParaulesProva, resultat.getFrequencies());
    }

}

//Classe Programada per: Arnau Tajahuerce
