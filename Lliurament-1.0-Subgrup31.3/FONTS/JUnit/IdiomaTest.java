package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;
import Excepcions.LlistaBuida;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class IdiomaTest {
    Alfabet alfabetProva;

    private Set<Character> lletresProva = new HashSet<Character>();

    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        alfabetProva = new Alfabet("AlfabetProva", lletresProva);
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
    public void creadoraIdiomaNomAlfabet() {
        Idioma resultat = new Idioma("IdiomaProva", alfabetProva);
        assertEquals("IdiomaProva", resultat.getNom());
        assertEquals(alfabetProva,resultat.getAlfabet());
        assertEquals(1,alfabetProva.numIdiomes());
    }

    @Test
    public void creadoraIdiomaNomAlfabetLlistaParaules() throws ExcepcionsCreadorTeclat {
        try {
            Idioma resultat = new Idioma("IdiomaProva", alfabetProva, "LlistaProva", llistaParaulesProva);
            assertEquals("IdiomaProva", resultat.getNom());
            assertEquals(alfabetProva,resultat.getAlfabet());
            assertEquals(llistaParaulesProva,resultat.getFrequencies());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
            assertTrue(false);
        } catch (LlistaBuida e2) {
            System.out.println("ERROR: " + e2.getMessage());
        }
    }

    @Test
    public void afegirLlistaFreqPred()  throws ExcepcionsCreadorTeclat{
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        try {
            LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
            idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
            assertEquals(llistaFreqProva, idiomaProva.getLlistaFreq());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
            assertTrue(false);
        } catch (LlistaBuida e4) {
            System.out.println("ERROR: " + e4.getMessage());
        }
    }

    @Test
    public void afegirLlistaFreqPredJaExistent() throws ExcepcionsCreadorTeclat {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        try {
            LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
            idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
            LlistaFrequencies llistaFreqProva1 = new LlistaFrequencies("LlistaProva1", idiomaProva, llistaParaulesProva);
            idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva1);
            assertEquals(llistaFreqProva, idiomaProva.getLlistaFreq());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
            assertTrue(false);
        } catch (LlistaBuida e4) {
            System.out.println("ERROR: " + e4.getMessage());
        }
    }

    @Test
    public void canviaLlistaFreq() throws ExcepcionsCreadorTeclat {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        try {
            LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
            idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
            LlistaFrequencies llistaFreqProva1 = new LlistaFrequencies("LlistaProva1", idiomaProva, llistaParaulesProva);
            idiomaProva.canviarLlistaFreqPredeterminada(llistaFreqProva1);
            assertEquals(llistaFreqProva1, idiomaProva.getLlistaFreq());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
            assertTrue(false);
        } catch (LlistaBuida e4) {
            System.out.println("ERROR: " + e4.getMessage());
        }
    }

    @Test
    public void getNom() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        assertEquals("IdiomaProva", idiomaProva.getNom());
    }

    @Test
    public void getAlfabet() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        assertEquals(alfabetProva, idiomaProva.getAlfabet());
    }

    @Test
    public void getLletres() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        assertEquals(lletresProva, idiomaProva.getLletres());
    }

    @Test
    public void getLlistaFreq() throws ExcepcionsCreadorTeclat{
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
        assertEquals(llistaFreqProva, idiomaProva.getLlistaFreq());
    }

    @Test
    public void getFrequencies() throws Exception {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva, llistaParaulesProva);
        assertEquals(llistaParaulesProva, idiomaProva.getFrequencies());
    }

}

//Classe Programada per: Arnau Tajahuerce