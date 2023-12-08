package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AlfabetTest {
    private Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
    }

    @Test
    public void creadoraAlfebetNomLletres() {
        Alfabet resultat = new Alfabet("AlfabetProva", lletresProva);
        assertEquals("AlfabetProva",resultat.getNomAlfabet());
        assertEquals(lletresProva,resultat.getLletres());
    }

    @Test
    public void afegirIdioma() {
        Alfabet resultat = new Alfabet("AlfabeProva", lletresProva);
        String nomIdioma = "IdiomaProva";
        resultat.afegirIdioma(nomIdioma);
        assertEquals(1,resultat.numIdiomes());
    }

    @Test
    public void treureIdioma() {
        Alfabet resultat = new Alfabet("AlfabeProva", lletresProva);
        resultat.afegirIdioma("IdiomaProva");
        assertEquals(1,resultat.numIdiomes());
        resultat.treureIdioma("IdiomaProva");
        assertEquals(0,resultat.numIdiomes());
    }

    @Test
    public void getNomAlfabet() {
        Alfabet resultat = new Alfabet("AlfabeProva", lletresProva);
        assertEquals("AlfabeProva",resultat.getNomAlfabet());
    }

    @Test
    public void getLletres() {
        Alfabet resultat = new Alfabet("AlfabeProva", lletresProva);
        assertEquals(lletresProva, resultat.getLletres());
    }

    @Test
    public void getNumLletres() {
        Alfabet resultat = new Alfabet("AlfabeProva", lletresProva);
        assertEquals(lletresProva.size(),resultat.getNumLletres());
    }

}

//Classe Programada per: Arnau Tajahuerce