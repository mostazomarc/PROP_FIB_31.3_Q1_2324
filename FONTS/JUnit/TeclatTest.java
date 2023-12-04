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
    public void omplirLletres() throws Exception {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva, "LlistaProva", llistaParaulesProva);
        try {
            llistaprova = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Test
    public void crearTeclatLlistaPropia() throws ExcepcionsCreadorTeclat {
        int numf = 3;
        int numc = 10;
        String e = "BranchAndBound";
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, numf, numc, e);
        char[][] expected = {{'g','c','f','i','l','o','h','b','k','z'},{'p','r','a','d','e','u','q','t','v','w'}, {'x','y','s','j','n','m', ' ', ' ', ' ', ' '}};
        assertEquals("agus", resultat.getNom());
        assertEquals(numf, (int) resultat.getDimX());
        assertEquals(numc, (int) resultat.getDimY());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals(expected, resultat.getDisposicio());
        assertTrue(true);
    }

    @Test
    public void crearTeclatLlistaIdioma() throws ExcepcionsCreadorTeclat {
        int numf = 3;
        int numc = 10;
        String e = "BranchAndBound";
        Teclat resultat = new Teclat("agus", idiomaProva, numf, numc, e);
        char[][] expected = {{'g','c','f','i','l','o','h','b','k','z'},{'p','r','a','d','e','u','q','t','v','w'}, {'x','y','s','j','n','m', ' ', ' ', ' ', ' '}};
        assertEquals("agus", resultat.getNom());
        assertEquals(numf, (int) resultat.getDimX());
        assertEquals(numc, (int) resultat.getDimY());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertArrayEquals(expected, resultat.getDisposicio());
        assertTrue(true);

    }


    //Pre:
    //Post:
    @Test
    public void getNomLlistaFreq() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
    }


    //Pre:
    //Post: es retorna el nom del teclat
    @Test
    public void getNom() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        assertEquals("agus", resultat.getNom());
    }

    //Pre:
    //Post: es retorna la disposició de teclat
    @Test
    public void getDisposicio() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        char[][] expected = {{'g','c','f','i','l','o','h','b','k','z'},{'p','r','a','d','e','u','q','t','v','w'}, {'x','y','s','j','n','m', ' ', ' ', ' ', ' '}};
        assertArrayEquals(expected, resultat.getDisposicio());
        assertTrue(true);

    }

    @Test
    public void getNomIdioma() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }

    @Test
    public void getDimX() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        assertEquals(3, (int)resultat.getDimX());
    }

    @Test
    public void getDimY() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        assertEquals(10, (int)resultat.getDimY());
    }

    @Test
    public void modificarLayout() throws ExcepcionsCreadorTeclat {
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, 3, 10, "BranchAndBound");
        resultat.modificarLayout(3,9);
        assertEquals(3, (int)resultat.getDimX());
        assertEquals(9, (int)resultat.getDimY());
    }
}

//Classe Programada per: Agustí Costabella

