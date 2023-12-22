package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlPersFreq;
import Domini.Alfabet;
import Domini.Idioma;
import Domini.LlistaFrequencies;
import Domini.Perfil;
import Excepcions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * PerfilTest es una classe que ens permet testejar les funcions de la classe Perfil
 * <p>
 * Les funcions que testeja són:
 * <ul>
 * <li>creadoraPerfilContrasenya</li>
 * <li>creadoraPerfil</li>
 * <li>getUsuari</li>
 * <li>getContrasenya</li>
 * <li>canviaUsuari</li>
 * <li>canviaContrasenya</li>
 * <li>afegirLlistaFreq</li>
 * <li>crearLlistaFreq</li>
 * <li>eliminarLlistaFreq</li>
 * <li>getNomAllLlistes</li>
 * <li>getNomIdiomaLlista</li>
 * <li>consultarLlista</li>
 * <li>crearTeclatLlistaPropia</li>
 * <li>crearTeclatLlistaIdioma</li>
 * <li>modificarTeclat</li>
 * <li>eliminaTeclat</li>
 * <li>modificarllista</li>
 * </ul>
 *
 * @see Perfil
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class PerfilTest {
    /**
     * Instancia de PerfilProva per fer les proves
     */
    Perfil perfilProva;
    /**
     * Instancia de IdiomaProva per fer les proves
     */
    Idioma idiomaProva;
    /**
     * Instancia de CtrlDomini
     */
    CtrlDomini controlador;
    /**
     * Instancia de CtrlPersFreq
     */
    CtrlPersFreq llistes;
    /**
     * LlistaFreq per fer les proves
     */
    LlistaFrequencies llista;
    /**
     * LlistaFreq per fer les proves
     */
    LlistaFrequencies llista2;
    /**
     * LlistaFreq per fer les proves
     */
    LlistaFrequencies llista3;
    /**
     * Llista de paraules i frequencies per fer les proves
     */
    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
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
     * Es crea un perfilProva
     */
    @Before
    public void crearPerfilProva() {
        perfilProva = new Perfil("Prova");
    }

    /**
     * Testeja creadora de perfil amb usuari i contrasenya
     */
    @Test
    public void creadoraPerfilContrasenya() {
        Perfil perfilResultat = new Perfil("Prova");
        assertEquals("Prova", perfilResultat.getUsuari());
    }

    /**
     * Testeja creadora de perfil amb usuari
     */
    @Test
    public void creadoraPerfil() {
        Perfil perfilResultat = new Perfil("Prova");
        assertEquals("Prova", perfilResultat.getUsuari());
    }

    /**
     * Testeja getUsuari
     */
    @Test
    public void getUsuari() {
        Perfil perfilResultat = new Perfil("Prova");
        assertEquals("Prova", perfilResultat.getUsuari());
    }

    /**
     * Testeja canviaUsuari
     */
    @Test
    public void canviaUsuari() {
        perfilProva.canviaUsuari("newProva");
        assertEquals("newProva", perfilProva.getUsuari());
        assertNotEquals("Prova", perfilProva.getUsuari());
    }

    /**
     * Testeja afegirLlistaFreq
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    public void afegirLlistaFreq() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms, perfilProva.getNomAllLlistes());
        assertEquals(llistaParaulesProva, perfilProva.consultaLlista("LlistaProva"));
    }

    /**
     * Testeja crearLlistaFreq
     * @throws Exception Si salta una excepció
     */
    @Test
    public void crearLlistaFreq() throws Exception {
        try {
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms, perfilProva.getNomAllLlistes());
    }

    /**
     * Testeja eliminarLlistaFreq
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminarLlistaFreq() throws Exception {
        try {
            LlistaFrequencies llista = perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            perfilProva.eliminaLlista(llista.getNom());
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        assertTrue(perfilProva.getNomAllLlistes().isEmpty());
    }

    /**
     * Testeja getNomAllLlistes
     * @throws Exception Si salta una excepció
     */
    @Test
    public void getNomAllLlistes() throws Exception {
        try {
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            perfilProva.afegirLlistaFreq("LlistaProva3", idiomaProva, llistaParaulesProva);
            perfilProva.afegirLlistaFreq("LlistaProva4", idiomaProva, llistaParaulesProva);
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        llistaNoms.add("LlistaProva3");
        llistaNoms.add("LlistaProva4");
        List<String> llistaResultat = perfilProva.getNomAllLlistes();
        Collections.sort(llistaNoms);
        Collections.sort(llistaResultat);
        assertEquals(llistaNoms, llistaResultat);
    }

    /**
     * Testeja getNomIdiomaLlista
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    public void getNomIdiomaLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        assertEquals("ESPAÑOL", perfilProva.getNomIdiomaLlista("LlistaProva"));
    }

    /**
     * Testeja consultarLlista
     * @throws ExcepcionsCreadorTeclat Si salta una excepció
     */
    @Test
    public void consultarLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        Map<String, Integer> resultat = perfilProva.consultaLlista("LlistaProva");
        assertEquals(resultat, llistaParaulesProva);
    }

    /**
     * Testeja crearTeclatLlistaPropia
     * @throws Exception Si salta una excepció
     */
    @Test
    public void crearTeclatLlistaPropia() throws Exception {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        perfilProva.crearTeclatLlistaPropia("TeclatProva", "LlistaProva", idiomaProva, 3, 10, "BranchAndBound");
        List<String> nomsEsperats = new ArrayList<>();
        nomsEsperats.add("TeclatProva");
        assertEquals(perfilProva.getNomsTeclats(), nomsEsperats);
    }

    /**
     * Testeja crearTeclatLlistaIdioma
     * @throws Exception Si salta una excepció
     */
    @Test
    public void crearTeclatLlistaIdioma() throws Exception {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10, "BranchAndBound");
        List<String> nomsEsperats = new ArrayList<>();
        nomsEsperats.add("TeclatProva");
        assertEquals(perfilProva.getNomsTeclats(), nomsEsperats);
    }

    /**
     * Testeja modificarTeclat
     */
    @Test
    public void modificarTeclat() {

    }

    /**
     * Testeja eliminarTeclat
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminaTeclat() throws Exception {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10, "BranchAndBound");
        perfilProva.eliminarTeclat("TeclatProva");
        assertFalse(perfilProva.getNomsTeclats().contains("TeclatProva"));
        assertTrue(perfilProva.getNomsTeclats().isEmpty());
    }

    /**
     * Testeja modificarllista
     * @throws Exception Si salta una excepció
     */
    @Test
    public void modificarllista() throws Exception {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        llistaParaulesProva.put("NovaParaula", 40);
        perfilProva.modificarLlista("LlistaProva", llistaParaulesProva);
        assertTrue(true);
    }

//    Excepcions limit
//    - Crear dos listas ambas el mateix nom
//    - Crear dos teclats amb el mateix nom
//    - Eliminar teclat que no existeix
//    - Eliminar lista que no existe
//    - Modificar lista que no existe

    /**
     * Testeja excepcio crear dos llistes amb el mateix nom
     * @throws Exception Si salta una excepció
     */
    @Test
    public void llistesMateixNom() throws Exception {
        try {
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            fail();
        } catch (LlistaFreqJaExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio crear dos teclats amb el mateix nom
     * @throws Exception Si salta una excepció
     */
    @Test
    public void teclatsMateixNom() throws Exception {
        perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
        try {
            perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10, "BranchAndBound");
            perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10, "BranchAndBound");
            fail();
        } catch (TeclatJaExisteix e2) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio eliminar teclat que no existeix
     */
    @Test
    public void eliminaTeclatInexistent() {
        try {
            perfilProva.eliminarTeclat("teclat");
            fail();
        } catch (TeclatNoExisteix e2) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio eliminar llista que no existeix
     * @throws Exception Si salta una excepció
     */
    @Test
    public void eliminaLlistaInexistent() throws Exception {
        try {
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            perfilProva.eliminaLlista("paprika"); //nom d'una llista no present a perfil
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio modificar llista que no existeix
     * @throws Exception Si salta una excepció
     */
    @Test
    public void modificaLlistaInexistent() throws Exception {
        try {
            perfilProva.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            perfilProva.modificarLlista("paprika", llistaParaulesProva);
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

}

//Classe Programada per: Marc