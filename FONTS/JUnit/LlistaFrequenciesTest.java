package JUnit;

import Domini.Alfabet;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LletraNoInclosa;
import Excepcions.LlistaBuida;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/** LlistaFrequenciesTest es una classe que ens permet testejar les funcions de la classe LlistaFrequencies
 * <p>
 * Les funcions que testeja són:
 * <ul>
 * <li>creadoraLlistaNom</li>
 * <li>creadoraLlistaIdiomaJaTePred</li>
 * <li>creadoraLlistaNomLlista</li>
 * <li>getNom</li>
 * <li>getNomIdioma</li>
 * <li>getFrequencies</li>
 * </ul>
 *
 * @see LlistaFrequencies
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class LlistaFrequenciesTest {
    /**
     * Instancia de IdiomaProva per fer les proves
     */
    Idioma idiomaProva;
    /**
     * Llista de paraules i frequencies per fer les proves
     */
    private Map<String, Integer> llistaParaulesProva = new HashMap<>();
    /**
     * Conjunt de lletres per fer les proves
     */
    private final Set<Character> lletresProva = new HashSet<Character>();

    /**
     * Es crea un idiomaProva
     */
    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva);
    }

    /**
     * S'omple la llistaParaulesProva
     */
    @Before
    public void omplirLlistaProva() {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
    }

    /**
     * Comprova que es crea una llista amb nom
     */
    @Test
    //Creadora LLista amb nom i getNom
    public void creadoraLlistaNom() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva);
        assertEquals("NovaLlista", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals(resultat, idiomaProva.getLlistaFreq());
    }

    /**
     * Testeja getNom
     */
    @Test
    public void getNom() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva);
        assertEquals("NovaLlista", resultat.getNom());
    }

    /**
     * Testeja getFrequencies
     */
    public void getFrequencies() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva);
        assertEquals(resultat, idiomaProva.getLlistaFreq());
    }

    /**
     * Testeja getNomIdioma
     */
    public void getNomIdioma() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva);
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
    }

    /**
     * Comprova que es crea una llista amb nom i idioma que ja té una llista predeterminada
     */
    @Test
    public void creadoraLlistaIdiomaJaTePred() {
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva);
        LlistaFrequencies resultat2 = new LlistaFrequencies("SegonaLlista", idiomaProva);
        assertEquals("NovaLlista", resultat.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertEquals(resultat, idiomaProva.getLlistaFreq());

        assertEquals("SegonaLlista", resultat2.getNom());
        assertEquals("ESPAÑOL", resultat.getNomIdioma());
        assertNotEquals(resultat2, idiomaProva.getLlistaFreq());
    }

    /**
     * Comprova que es crea una llista amb nom, idioma i llista de paraules i frequencies
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    //Creadora LLista amb nom i Llista de paraules i getFrequencies
    public void creadoraLlistaNomLlista() throws ExcepcionsCreadorTeclat {
        try {
            LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva, llistaParaulesProva);
            assertEquals("NovaLlista", resultat.getNom());
            assertEquals(llistaParaulesProva, resultat.getFrequencies());
            assertEquals("ESPAÑOL", resultat.getNomIdioma());
            assertEquals(resultat, idiomaProva.getLlistaFreq());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
            fail();
        }
    }

    //### Extrems

//    - Lista de freuquencies con letras que no son part de l’idioma
//    - Crear una lista con una lista vacía


    /**
     * Comprova Excepcio LletraNoInclosa
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    public void llistaLletresNoIdioma() throws ExcepcionsCreadorTeclat {
        llistaParaulesProva.put("Caña", 30); //ficem la lletra ñ no inclosa a l'Alfabet Prova
        try {
            LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva, llistaParaulesProva);
            fail(); //si arriba aqui no ha detectat la lletra no inclosa
        } catch (LletraNoInclosa e) {
            assertTrue(true); //si arriba aqui ha detectat la lletra no inclosa
        }
    }

    /**
     * Comprova Excepcio LlistaBuida
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    public void llistaBuida() throws ExcepcionsCreadorTeclat {
        llistaParaulesProva = new HashMap<>();
        try {
            LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", idiomaProva, llistaParaulesProva);
            fail(); //si arriba aqui no ha detectat la lletra no inclosa
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (LlistaBuida e3) {
            assertTrue(true);
        }
    }

}

//Classe Programada per: Marc
