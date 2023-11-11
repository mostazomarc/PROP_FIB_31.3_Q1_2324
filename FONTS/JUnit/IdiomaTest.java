package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class IdiomaTest {
    Alfabet alfabetProva;
    LlistaFrequencies llistaFreqProva;

    private Set<Character> lletresProva = new HashSet<Character>();

    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        alfabetProva = new Alfabet("Prova", lletresProva);
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
        Idioma resultat = new Idioma("Prova", alfabetProva);
        assertEquals("Prova", resultat.getNom());
        assertEquals(alfabetProva,resultat.getAlfabet());
    }

    @Test
    public void creadoraIdiomaNomAlfabetLlistaParaules() {
        Idioma resultat = new Idioma("IdiomaProva", alfabetProva, "LlistaProva", llistaParaulesProva);
        assertEquals("IdiomaProva", resultat.getNom());
        assertEquals(alfabetProva,resultat.getAlfabet());
        assertEquals(llistaParaulesProva,resultat.getFrequencies());
    }

    @Test
    public void afegirLlistaFreqPred() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
        assertEquals(llistaFreqProva, idiomaProva.getLlistaFreq());
    }

    @Test
    public void afegirLlistaFreqPredJaExistent() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
        LlistaFrequencies llistaFreqProva1 = new LlistaFrequencies("LlistaProva1", idiomaProva, llistaParaulesProva);
        idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva1);
        assertEquals(llistaFreqProva, idiomaProva.getLlistaFreq());
    }

    @Test
    public void canviaLlistaFreq() {
        Idioma idiomaProva = new Idioma("IdiomaProva", alfabetProva);
        LlistaFrequencies llistaFreqProva = new LlistaFrequencies("LlistaProva", idiomaProva, llistaParaulesProva);
        idiomaProva.afegirLlistaFreqPredeterminada(llistaFreqProva);
        LlistaFrequencies llistaFreqProva1 = new LlistaFrequencies("LlistaProva1", idiomaProva, llistaParaulesProva);
        idiomaProva.canviarLlistaFreqPredeterminada(llistaFreqProva1);
        assertEquals(llistaFreqProva1, idiomaProva.getLlistaFreq());
    }

}