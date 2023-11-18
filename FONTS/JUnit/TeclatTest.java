package JUnit;

import Domini.*;
import Domini.LlistaFrequencies;
import Stubs.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TeclatTest {

    private String nom;
    private int dimX;
    private int dimY;
    private char[][] disposicio;
    private Idioma idioma;
    private LlistaFrequencies llistafreq;

    private Set<Character> lletresProva = new HashSet<Character>();

    Idioma idiomaProva;

    private Map<String, Integer> llistaParaulesProva = new HashMap<>();


    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL",AlfabetProva);
    }

    @Before
    public void omplirLlistaProva() {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
    }
    @Test
    public void crearTeclatLlistaPròpia() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals("nom",resultat.getNomTeclat());
        assertEquals(teclatQWERTY,resultat.getTeclat());
    }

    @Test
    public void crearTeclatLlistaIdioma() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals(teclatQWERTY,resultat.getTeclat());
    }

    @Test
    public void obtenirNomTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals("nom",resultat.getNomTeclat());
    }

    @Test
    public void canviarTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat prova = new Teclat("nom",llista,idioma);
        assertNotNull(prova.canviaDisposicioTeclat());
    }
}