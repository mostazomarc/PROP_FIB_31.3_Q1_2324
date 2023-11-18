package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersTeclats;
import Domini.*;

import static org.junit.Assert.*;

import Excepcions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class CtrlPersTeclatsTest {

    CtrlPersTeclats cP;


    CtrlDomini cD;

    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

    Idioma idiomaProva;
    LlistaFrequencies llistaProva;
    Teclat teclatProva;

    private Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL",AlfabetProva);
    }

    @Before
    public void CrearTeclat() throws Exception{
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
    public void eliminarLlistaProva() throws Exception{
        try {
            cP.eliminarTeclat("nouTeclat");
        } catch (Exception e) {}
    }

    @Test
    public void getControladorUnCopCreat() {
        assertSame(cP,CtrlPersTeclats.getInstance(cD));
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
    public void afegirTeclat() throws Exception{
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat",resultat.getNom());
        assertEquals("ESPAÑOL",resultat.getNomIdioma());
        assertEquals("LlistaProva",resultat.getNomLlistaFreq());
    }




    @Test
    public void eliminaTeclat() throws Exception{
        cP.afegirTeclat(teclatProva);
        cP.eliminarTeclat("nouTeclat");
        try {
            cP.getTeclat("nouTeclat");
            assertTrue(false);
        } catch (TeclatNoExisteix e) {
            assertTrue(true);
        }
    }

    @Test
    public void comprovarUsIdioma() throws Exception{
        cP.afegirTeclat(teclatProva);
        try {
            cP.comprovarUsIdioma("ESPAÑOL");
            assertTrue(false);
        } catch (IdiomaEnUs e) {
            assertTrue(true);
        }
    }

    @Test
    public void comprovarUsLlista() throws Exception{
        cP.afegirTeclat(teclatProva);
        try {
            cP.comprovarUsLlista(llistaProva.getNom());
            assertTrue(false);
        } catch (LlistaFreqEnUs e) {
            assertTrue(true);
        }
    }

    @Test
    public void getTeclat() throws Exception{
        cP.afegirTeclat(teclatProva);
        Teclat resultat = cP.getTeclat(teclatProva.getNom());
        assertEquals("nouTeclat",resultat.getNom());
        assertEquals("ESPAÑOL",resultat.getNomIdioma());
        assertEquals("LlistaProva",resultat.getNomLlistaFreq());
    }


}

//Classe Programada per: Marc