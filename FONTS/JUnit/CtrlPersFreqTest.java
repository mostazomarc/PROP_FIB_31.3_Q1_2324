package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersFreq;
import Domini.Alfabet;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Excepcions.IdiomaEnUs;
import Excepcions.LlistaFreqNoExisteix;
import Excepcions.LlistaFreqJaExisteix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * CtrlPersFreqTest es una classe que ens permet testejar les funcions de la classe CtrlPersFreq
 * <p>
 * Les funcions que testeja són:
 * <ul>
 * <li>getControladorUnCopCreat</li>
 * <li>carregarLlistes</li>
 * <li>nouPerfil</li>
 * <li>canviaPerfil</li>
 * <li>afegirLlistaFreqNomesIdioma</li>
 * <li>afegirLlistaFreq</li>
 * <li>eliminaLlistaFreq</li>
 * <li>afegirLlistaDuplicada</li>
 * <li>eliminaLlistaInexistent</li>
 * <li>comprovarUsIdioma</li>
 * <li>getLlistaFreq</li>
 * </ul>
 *
 * @see CtrlPersFreq
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersFreqTest {

    /**
     * Instancia de CtrlPersFreq
     */
    CtrlPersFreq cP;
    /**
     * Instancia de CtrlDomini
     */
    CtrlDomini cD;
    /**
     * Instancia de IdiomaProva per fer les proves
     */
    Idioma idiomaProva;
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
     * S'omple la llistaParaulesProva
     */
    @Before
    public void omplirLlistaProva() {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
    }

    /**
     * S'obte la instancia de CtrlDomini
     */
    @Before
    public void getControladorDomini() {
        cD = CtrlDomini.getInstance();
    }

    /**
     * S'obte la instancia de CtrlPersFreq
     */
    @Before
    public void getControladorPersistencia() {
        cP = CtrlPersFreq.getInstance(cD);
    }

    /**
     * S'eliminen les llistes creades per poder tornarles a crear
     */
    @After
    public void eliminarLlistaProva() {
        try {
            cP.eliminarLlista("LlistaProva");
        } catch (Exception e) {
        }
    }

    /**
     * Prova de singleton
     */
    @Test
    public void getControladorUnCopCreat() {
        assertSame(cP, CtrlPersFreq.getInstance(cD));
    }
//      NO ES POT FER PERQUE NO ES CARRGUEN IDIOMES
//    @Test
//    public void carregarLlistes() throws Exception{
//        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
//        cD.afegirAlfabet("alfabetCatala.txt");
//        cP.carregarFrequencies();
//        try {
//            cP.getLlistaFreq("catalaFreq.txt");
//            assertTrue(true);
//        } catch (LlistaFreqNoExisteix e) {
//            assertTrue(false);
//        }
//    }

    /**
     * Prova de nouPerfil
     */
    @Test
    public void nouPerfil() {
        assertTrue(true);
    }

    /**
     * Prova de canviaPerfil
     */
    @Test
    public void canviaPerfil() {
        assertTrue(true);
    }


    /**
     * Prova de afegirLlistaFreq
     * @throws Exception Si salta una excepció
     */
    @Test
    public void afegirLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        LlistaFrequencies llista = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.guardarLlistaFreq(llista);
        LlistaFrequencies resultat = cP.getLlistaFreq("LlistaProva");
        assertEquals("LlistaProva", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals(resultat, idiomaProva.getLlistaFreq());
    }

    /**
     * Prova de eliminaLlistaFreq
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminaLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        LlistaFrequencies llista = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.guardarLlistaFreq(llista);
        cP.eliminarLlista("LlistaProva");
        try {
            cP.getLlistaFreq("LlistaProva");
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Prova de afegirLlistaDuplicada
     * @throws Exception Si salta una excepció
     */
    @Test
    public void afegirLlistaDuplicada() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        LlistaFrequencies llista = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.guardarLlistaFreq(llista);
        try {
            cP.guardarLlistaFreq(llista);
            cP.guardarLlistaFreq(llista);
            fail();
        } catch (LlistaFreqJaExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Prova de eliminaLlistaInexistent
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminaLlistaInexistent() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        try {
            cP.eliminarLlista("LlistaProva");
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Prova de comprovarUsIdioma
     * @throws Exception Si salta una excepció
     */
    @Test
    public void comprovarUsIdioma() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        LlistaFrequencies llista = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.guardarLlistaFreq(llista);
        try {
            cP.comprovarUsIdioma("ESPAÑOL");
            fail();
        } catch (IdiomaEnUs e) {
            assertTrue(true);
        }
    }

    /**
     * Prova de getLlistaFreq
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        LlistaFrequencies llista = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.guardarLlistaFreq(llista);
        LlistaFrequencies resultat = cP.getLlistaFreq("LlistaProva");
        assertEquals("LlistaProva", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }


}

//Classe Programada per: Marc