package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LlistaFrequenciesTest {
    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

    Idioma idiomaProva;

    private Set<Character> lletresProva = new HashSet<Character>();

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
    //Creadora LLista amb nom i getNom
    public void creadoraLlistaNom() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista",idiomaProva);
        assertEquals("NovaLlista",resultat.getNom());
        assertEquals(idiomaProva,resultat.getIdioma());
        assertEquals(resultat,idiomaProva.getLlistaFreq());
    }

    @Test
    //Creadora LLista amb nom i idioma ja té llista Predeterminada
    public void creadoraLlistaIdiomaJaTePred() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista",idiomaProva);
        LlistaFrequencies resultat2 = new LlistaFrequencies("SegonaLlista",idiomaProva);
        assertEquals("NovaLlista",resultat.getNom());
        assertEquals(idiomaProva,resultat.getIdioma());
        assertEquals(resultat,idiomaProva.getLlistaFreq());

        assertEquals("SegonaLlista",resultat2.getNom());
        assertEquals(idiomaProva,resultat2.getIdioma());
        assertNotEquals(resultat2,idiomaProva.getLlistaFreq());
    }

    @Test
    //Creadora LLista amb nom i Llista de paraules i getFrequencies
    public void creadoraLlistaNomLlista() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista",idiomaProva, llistaParaulesProva);
        assertEquals("NovaLlista",resultat.getNom());
        assertEquals(llistaParaulesProva,resultat.getFrequencies());
        assertEquals(idiomaProva,resultat.getIdioma());
        assertEquals(resultat,idiomaProva.getLlistaFreq());
    }

    @Test
    //Insertar llista de frequencies a una llista ja creada
    public void insertarFrequencies() {

        LlistaFrequencies llistaProva = new LlistaFrequencies("NovaLlista",idiomaProva);
        llistaProva.insertarFrequencies(llistaParaulesProva);
        assertEquals(llistaParaulesProva,llistaProva.getFrequencies());
    }


}
