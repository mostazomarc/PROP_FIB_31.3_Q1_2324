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
     * S'eliminen les llistes creades per poder tornarles a crear
     */
    @After
    public void eliminarLlistaProva() throws Exception {
        try {
            llistes.eliminarLlista("LlistaProva");
            llistes.eliminarLlista("LlistaProva3");
            llistes.eliminarLlista("LlistaProva4");
        } catch (Exception e) {
        }
    }

    /**
     * Es crea un perfilProva
     */
    @Before
    public void crearPerfilProva() {
        controlador = CtrlDomini.getInstance();
        llistes = CtrlPersFreq.getInstance(controlador);
        try {
            llista = llistes.afegirLlistaFreq("LlistaProva", idiomaProva, llistaParaulesProva);
            llista2 = llistes.afegirLlistaFreq("LlistaProva3", idiomaProva, llistaParaulesProva);
            llista3 = llistes.afegirLlistaFreq("LlistaProva4", idiomaProva, llistaParaulesProva);
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e2) {
            System.out.println("ERROR: " + e2.getMessage());
        }
        perfilProva = new Perfil("Prova", "12345");
    }

    /**
     * Testeja creadora de perfil amb usuari i contrasenya
     */
    @Test
    public void creadoraPerfilContrasenya() {
        Perfil perfilResultat = new Perfil("Prova", "12345");
        assertEquals("Prova", perfilResultat.getUsuari());
        assertEquals("12345", perfilResultat.getContrasenya());
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
     * Testeja getContrasenya
     */
    @Test
    public void getContrasenya() {
        Perfil perfilResultat = new Perfil("Prova", "12345");
        assertEquals("12345", perfilResultat.getContrasenya());
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
     * Testeja canviaContrasenya
     */
    @Test
    public void canviaContrasenya() {
        perfilProva.canviaContrasenya("newContrasenya");
        assertEquals("newContrasenya", perfilProva.getContrasenya());
        assertNotEquals("12345", perfilProva.getContrasenya());
    }

    /**
     * Testeja afegirLlistaFreq
     */
    @Test
    public void afegirLlistaFreq() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms, perfilProva.getNomAllLlistes());
        assertEquals(llistaParaulesProva, perfilProva.consultaLlista("LlistaProva"));
    }

    /**
     * Testeja crearLlistaFreq
     */
    @Test
    public void crearLlistaFreq() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms, perfilProva.getNomAllLlistes());
    }

    /**
     * Testeja eliminarLlistaFreq
     */
    @Test
    public void eliminarLlistaFreq() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.eliminaLlista(llista.getNom());
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        assertTrue(perfilProva.getNomAllLlistes().isEmpty());
    }

    /**
     * Testeja getNomAllLlistes
     */
    @Test
    public void getNomAllLlistes() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.afegirLlistaFreq(llista2);
            perfilProva.afegirLlistaFreq(llista3);
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
     */
    @Test
    public void getNomIdiomaLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        assertEquals("ESPAÑOL", perfilProva.getNomIdiomaLlista("LlistaProva"));
    }

    /**
     * Testeja consultarLlista
     */
    @Test
    public void consultarLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        Map<String, Integer> resultat = perfilProva.consultaLlista("LlistaProva");
        assertEquals(resultat, llistaParaulesProva);
    }

    /**
     * Testeja crearTeclatLlistaPropia
     */
    @Test
    public void crearTeclatLlistaPropia() throws Exception {
        perfilProva.afegirLlistaFreq(llista);
        perfilProva.crearTeclatLlistaPropia("TeclatProva", "LlistaProva", idiomaProva, 3, 10);
        List<String> nomsEsperats = new ArrayList<>();
        nomsEsperats.add("TeclatProva");
        assertEquals(perfilProva.getNomsTeclats(), nomsEsperats);
    }

    /**
     * Testeja crearTeclatLlistaIdioma
     */
    @Test
    public void crearTeclatLlistaIdioma() throws Exception {
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
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
     */
    @Test
    public void eliminaTeclat() throws Exception {
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
        perfilProva.eliminarTeclat("TeclatProva");
        assertFalse(perfilProva.getNomsTeclats().contains("TeclatProva"));
        assertTrue(perfilProva.getNomsTeclats().isEmpty());
    }

    /**
     * Testeja modificarllista
     */
    @Test
    public void modificarllista() throws Exception {
        perfilProva.afegirLlistaFreq(llista);
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
     */
    @Test
    public void llistesMateixNom() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.afegirLlistaFreq(llista);
            fail();
        } catch (LlistaFreqJaExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio crear dos teclats amb el mateix nom
     */
    @Test
    public void teclatsMateixNom() throws Exception {
        try {
            perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
            perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
            fail();
        } catch (TeclatJaExisteix e2) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio eliminar teclat que no existeix
     */
    @Test
    public void eliminaTeclatInexistent() throws Exception {
        try {
            perfilProva.eliminarTeclat("teclat");
            fail();
        } catch (TeclatNoExisteix e2) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio eliminar llista que no existeix
     */
    @Test
    public void eliminaLlistaInexistent() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.eliminaLlista("paprika"); //nom d'una llista no present a perfil
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

    /**
     * Testeja excepcio modificar llista que no existeix
     */
    @Test
    public void modificaLlistaInexistent() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.modificarLlista("paprika", llistaParaulesProva);
            fail();
        } catch (LlistaFreqNoExisteix e) {
            assertTrue(true);
        }
    }

}

//Classe Programada per: Marc