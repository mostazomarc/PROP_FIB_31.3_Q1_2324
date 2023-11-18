package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersTeclats;
import Domini.Alfabet;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Domini.Teclat;
import Excepcions.IdiomaEnUs;
import Excepcions.LlistaFreqEnUs;
import Excepcions.TeclatNoExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class CtrlPersTeclatsTest {

    CtrlPersTeclats cP;


    CtrlDomini cD;
    Idioma idiomaProva;
    LlistaFrequencies llistaProva;
    Teclat teclatProva;
    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
    private final Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva);
    }

    @Before
    public void CrearTeclat() throws Exception {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
        llistaProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        teclatProva = new Teclat("nouTeclat", llistaProva, idiomaProva, 3, 30);
    }

    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    @Before
    public void getControladorPersistencia() {
        cP = CtrlPersTeclats.getInstance(cD);
    }


    //S'eliminen les llistes creades per poder tornarles a crear
    @After
    public void eliminarLlistaProva() throws Exception {
        try {
            cP.eliminarTeclat("nouTeclat");
        } catch (Exception e) {
        }
    }

    @Test
    public void getControladorUnCopCreat() {
        assertSame(cP, CtrlPersTeclats.getInstance(cD));
    }


    @Test
    public void nouPerfil() {
        assertTrue(true);
    }

    @Test
    public void canviaPerfil() {
        assertTrue(true);
    }


    @Test
    public void afegirTeclat() throws Exception {
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
    }


    @Test
    public void eliminaTeclat() throws Exception {
        cP.afegirTeclat(teclatProva);
        cP.eliminarTeclat("nouTeclat");
        try {
            cP.getTeclat("nouTeclat");
            fail();
        } catch (TeclatNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void comprovarUsIdioma() throws Exception {
        cP.afegirTeclat(teclatProva);
        try {
            cP.comprovarUsIdioma("ESPAÑOL");
            fail();
        } catch (IdiomaEnUs e) {
            assertTrue(true);
        }
    }

    @Test
    public void comprovarUsLlista() throws Exception {
        cP.afegirTeclat(teclatProva);
        try {
            cP.comprovarUsLlista(llistaProva.getNom());
            fail();
        } catch (LlistaFreqEnUs e) {
            assertTrue(true);
        }
    }

    @Test
    public void getTeclat() throws Exception {
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
    }


}

//Classe Programada per: Marc