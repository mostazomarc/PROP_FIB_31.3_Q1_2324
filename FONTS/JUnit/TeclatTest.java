package JUnit;

import Domini.*;
import Domini.LlistaFrequencies;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;
import Stubs.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TeclatTest {

    private Set<Character> lletresProva = new HashSet<Character>();

    Idioma idiomaProva;

    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

    LlistaFrequencies llistaprova;


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
    public void crearTeclatLlistaPropia() throws ExcepcionsCreadorTeclat {
        try {
            llistaprova = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10);
        assertEquals("agus", resultat.getNom());
        assertEquals(3, resultat.getDimX());
        assertEquals(10, resultat.getDimY());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }
}