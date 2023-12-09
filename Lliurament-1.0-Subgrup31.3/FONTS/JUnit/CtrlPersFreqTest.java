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

public class CtrlPersFreqTest {

    CtrlPersFreq cP;


    CtrlDomini cD;
    Idioma idiomaProva;
    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
    private final Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva);
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
        cP = CtrlPersFreq.getInstance(cD);
    }

    //S'eliminen les llistes creades per poder tornarles a crear
    @After
    public void eliminarLlistaProva() throws Exception {
        try {
            cP.eliminarLlista("LlistaProva");
        } catch (Exception e) {
        }
    }

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

    @Test
    public void nouPerfil() {
        assertTrue(true);
    }

    @Test
    public void canviaPerfil() {
        assertTrue(true);
    }


    @Test
    public void afegirLlistaFreqNomesIdioma() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        cP.afegirLlistaFreq("LlistaProva", idiomaProva);
        LlistaFrequencies resultat = cP.getLlistaFreq("LlistaProva");
        assertEquals("LlistaProva", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }

    @Test
    public void afegirLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        cP.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        LlistaFrequencies resultat = cP.getLlistaFreq("LlistaProva");
        assertEquals("LlistaProva", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals(resultat, idiomaProva.getLlistaFreq());
    }


    @Test
    public void eliminaLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        cP.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        cP.eliminarLlista("LlistaProva");
        try {
            cP.getLlistaFreq("LlistaProva");
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void afegirLlistaDuplicada() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);
        cP.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        try {
            cP.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            cP.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            fail();
        } catch (LlistaFreqJaExisteix e) {
            assertTrue(true);
        }
    }

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

    @Test
    public void comprovarUsIdioma() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        cP.afegirLlistaFreq("LlistaProva", idiomaProva);
        try {
            cP.comprovarUsIdioma("ESPAÑOL");
            fail();
        } catch (IdiomaEnUs e) {
            assertTrue(true);
        }
    }

    @Test
    public void getLlistaFreq() throws Exception {
        CtrlPersFreq cP = CtrlPersFreq.getInstance(cD);

        cP.afegirLlistaFreq("LlistaProva", idiomaProva);
        LlistaFrequencies resultat = cP.getLlistaFreq("LlistaProva");
        assertEquals("LlistaProva", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }


}

//Classe Programada per: Marc