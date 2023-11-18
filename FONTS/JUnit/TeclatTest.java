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
    public void omplirLletres() throws LletraNoInclosa {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
        idiomaProva = new Idioma("ESPAÑOL",AlfabetProva, "LlistaProva", llistaParaulesProva);
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
        Teclat resultat = new Teclat("agus", llistaprova, idiomaProva, numf, numc);
        assertEquals("agus", resultat.getNom());
        assertEquals(numf, (int)resultat.getDimX());
        assertEquals(numc, (int)resultat.getDimY());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }

    @Test
    public void crearTeclatLlistaIdioma() throws ExcepcionsCreadorTeclat {
        int numf = 3;
        int numc = 10;
        Teclat resultat = new Teclat("agus", idiomaProva, numf, numc);
        assertEquals("agus", resultat.getNom());
        assertEquals(numf, (int)resultat.getDimX());
        assertEquals(numc, (int)resultat.getDimY());
        assertEquals("LlistaProva", resultat.getNomLlistaFreq());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }


    //Pre:
    //Post:
//    @Test
//    public String getNomLlistaFreq() {return llistafreq.getNom(); }
//
//
//    //Pre:
//    //Post: es retorna el nom del teclat
//    @Test
//    public String getNom() {
//        return nom;
//    }
//
//    //Pre:
//    //Post: es retorna la disposició de teclat
//    @Test
//    public char[][] getDisposicio() {
//        return disposicio;
//    }
//
//    @Test
//    public String getNomIdioma() {
//        return idioma.getNom();
//    }
//
//    @Test
//    public Integer getDimX() {return dimX; }
//
//    @Test
//    public Integer getDimY() {return dimY; }
}