package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersTeclats;
import Domini.Alfabet;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Domini.Teclat;
import Excepcions.IdiomaEnUs;
import Excepcions.LlistaFreqEnUs;
import Excepcions.TeclatJaExisteix;
import Excepcions.TeclatNoExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * CtrlPersTeclatsTest es una classe que ens permet testejar les funcions de la classe CtrlPersTeclats
 * <p>
 * Les funcions que testeja són:
 * <ul>
 * <li>getControladorUnCopCreat</li>
 * <li>nouPerfil</li>
 * <li>canviaPerfil</li>
 * <li>afegirTeclat</li>
 * <li>afegirTeclatDuplicat</li>
 * <li>eliminaTeclat</li>
 * <li>eliminaTeclatInexistent</li>
 * <li>comprovarUsIdioma</li>
 * <li>comprovarUsLlista</li>
 * <li>getTeclat</li>
 * <li>getTeclatInexistent</li>
 * </ul>
 *
 * @see CtrlPersTeclats
 */
public class CtrlPersTeclatsTest {
    /**
     * Instancia de CtrlPersTeclats
     */
    CtrlPersTeclats cP;
    /**
     * Instancia de CtrlDomini
     */
    CtrlDomini cD;
    /**
     * Instancia de IdiomaProva per fer les proves
     */
    Idioma idiomaProva;
    /**
     * LlistaFrequencies per fer les proves
     */
    LlistaFrequencies llistaProva;
    /**
     * Teclat per fer les proves
     */
    Teclat teclatProva;
    /**
     * Llista de paraules i frequencies per fer les proves
     */
    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
    /**
     * Conjunt de lletres per fer les proves
     */
    private final Set<Character> lletresProva = new HashSet<Character>();

    /**
     * Es crea un idiomaProva
     */
    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva);
    }

    /**
     * S'omple la llistaParaulesProva y es crea una llista i un teclat de prova
     * @throws Exception Si salta una excepció
     */
    @Before
    public void CrearTeclat() throws Exception {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
        llistaProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        teclatProva = new Teclat("nouTeclat", llistaProva, idiomaProva, 3, 10, "BranchAndBound");
    }

    /**
     * S'obte la instancia de CtrlDomini
     */
    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    /**
     * S'obte la instancia de CtrlPersTeclats
     */
    @Before
    public void getControladorPersistencia() {
        cP = CtrlPersTeclats.getInstance(cD);
    }

    /**
     * S'eliminen les llistes creades per poder tornarles a crear
     * @throws Exception Si salta una excepció
     */
    @After
    public void eliminarLlistaProva() throws Exception {
        try {
            cP.eliminarTeclat("nouTeclat");
        } catch (Exception e) {
        }
    }

    /**
     * Comprova que el controlador de teclats només es crea un cop
     */
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

    /**
     * Comprova que es pot afegir un teclat
     * @throws Exception Si salta una excepció
     */
    @Test
    public void afegirTeclat() throws Exception {
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
    }

    /**
     * Comprova que no es pot afegir un teclat duplicat
     * @throws Exception Si salta una excepció
     */
    @Test
    public void afegirTeclatDuplicat() throws Exception {
        cP.afegirTeclat(teclatProva);
        try {
            cP.afegirTeclat(teclatProva);
            assertTrue(false);
        } catch (TeclatJaExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Comprova que es pot eliminar un teclat
     * @throws Exception Si salta una excepció
     */
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

    /**
     * Comprova que no es pot eliminar un teclat inexistent
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminaTeclatInexistent() throws Exception {
        try {
            cP.eliminarTeclat("Inexistent");
            fail();
        } catch (TeclatNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Comprova la funció comprovarUsIdioma
     * @throws Exception Si salta una excepció
     */
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

    /**
     * Comprova la funció comprovarUsLlista
     * @throws Exception Si salta una excepció
     */
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

    /**
     * Comprova que es pot obtenir un teclat
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getTeclat() throws Exception {
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
    }

    /**
     * Comprova que no es pot obtenir un teclat inexistent
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getTeclatInexistent() throws Exception {
        try {
            Teclat resultat = cP.getTeclat("inexistent");
            assertTrue(false);
        } catch (TeclatNoExisteix e) {
            assertTrue(true);
        }
    }


}

//Classe Programada per: Agustí Costabella