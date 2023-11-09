package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AlfabetTest {
    private String nomAlfabet;
    private Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
    }

    @Test
    public void creadoraAlfebetNomLletres() {
        Alfabet resultat = new Alfabet("Llatí", lletresProva);
        assertEquals("Llatí",resultat.getNomAlfabet());
        assertEquals(lletresProva,resultat.getLletres());
    }

    @Test
    public void getNumLletres() {
        int numLletres = 26;
        Alfabet resultat = new Alfabet("Llatí", lletresProva);
        assertEquals(numLletres,resultat.getNumLletres());
    }

}