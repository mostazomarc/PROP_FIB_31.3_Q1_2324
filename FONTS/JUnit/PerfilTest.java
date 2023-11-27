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

public class PerfilTest {
    Perfil perfilProva;
    Idioma idiomaProva;
    CtrlDomini controlador;
    CtrlPersFreq llistes;
    LlistaFrequencies llista;
    LlistaFrequencies llista2;
    LlistaFrequencies llista3;
    private final Map<String, Integer> llistaParaulesProva = new HashMap<>();
    private final Set<Character> lletresProva = new HashSet<Character>();

    @Before
    public void omplirLletres() {
        for (char lletra = 'a'; lletra <= 'z'; lletra++) lletresProva.add(lletra);
        Alfabet AlfabetProva = new Alfabet("Prova", lletresProva);
        idiomaProva = new Idioma("ESPAÑOL", AlfabetProva);
    }

    @Before
    public void omplirLlistaProva() {
        llistaParaulesProva.put("Hola", 10);
        llistaParaulesProva.put("Casa", 20);
        llistaParaulesProva.put("Adeu", 30);
        llistaParaulesProva.put("Menjar", 30);
        llistaParaulesProva.put("Fideu", 30);
    }

    //S'eliminen les llistes creades per poder tornarles a crear
    @After
    public void eliminarLlistaProva() throws Exception {
        try {
            llistes.eliminarLlista("LlistaProva");
            llistes.eliminarLlista("LlistaProva3");
            llistes.eliminarLlista("LlistaProva4");
        } catch (Exception e) {
        }
    }

    @Before
    public void crearPerfilProva() throws ExcepcionsCreadorTeclat {
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
        perfilProva = new Perfil("Prova", "12345", controlador);
    }


    @Test
    //Creadora Perfil amb usuari i contrasenya i getUsuari i getContrasenya
    public void creadoraPerfilContrasenya() {
        Perfil perfilResultat = new Perfil("Prova", "12345", controlador);
        assertEquals("Prova", perfilResultat.getUsuari());
        assertEquals("12345", perfilResultat.getContrasenya());
    }

    @Test
    //Creadora Perfil amb usuari i getNom
    public void creadoraPerfil() {
        Perfil perfilResultat = new Perfil("Prova", controlador);
        assertEquals("Prova", perfilResultat.getUsuari());
    }

    @Test
    //GetUsuari
    public void getUsuari() {
        Perfil perfilResultat = new Perfil("Prova", controlador);
        assertEquals("Prova", perfilResultat.getUsuari());
    }

    @Test
    //GetContrasenya
    public void getContrasenya() {
        Perfil perfilResultat = new Perfil("Prova", "12345", controlador);
        assertEquals("12345", perfilResultat.getContrasenya());
    }

    @Test
    //canvia usuari
    public void canviaUsuari() {
        perfilProva.canviaUsuari("newProva");
        assertEquals("newProva", perfilProva.getUsuari());
        assertNotEquals("Prova", perfilProva.getUsuari());
    }

    @Test
    //canvia contrasenya
    public void canviaContrasenya() {
        perfilProva.canviaContrasenya("newContrasenya");
        assertEquals("newContrasenya", perfilProva.getContrasenya());
        assertNotEquals("12345", perfilProva.getContrasenya());
    }

    @Test
    //afegir llista freq amb nom i llista de paraules
    public void afegirLlistaFreq() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms, perfilProva.getNomAllLlistes());
        assertEquals(llistaParaulesProva, perfilProva.consultaLlista("LlistaProva"));
    }

    @Test
    //afegir llista nomes amb nom
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

    @Test
    //eliminar llista amb nom
    public void eliminarLlistaFreq() throws Exception {
        try {
            perfilProva.afegirLlistaFreq(llista);
            perfilProva.eliminaLlista(llista.getNom());
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        assertTrue(perfilProva.getNomAllLlistes().isEmpty());
    }

    @Test
    //get el nomes de totes les llistes del perfil
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

    @Test
    //obtenir la llista de paraules i frequencies
    public void getNomIdiomaLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        assertEquals("ESPAÑOL", perfilProva.getNomIdiomaLlista("LlistaProva"));
    }

    @Test
    //obtenir la llista de paraules i frequencies
    public void consultarLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq(llista);
        Map<String, Integer> resultat = perfilProva.consultaLlista("LlistaProva");
        assertEquals(resultat, llistaParaulesProva);
    }

    @Test
    //obtenir la llista de paraules i frequencies
    public void crearTeclatLlistaPropia() throws Exception {
        perfilProva.afegirLlistaFreq(llista);
        perfilProva.crearTeclatLlistaPropia("TeclatProva", "LlistaProva", idiomaProva, 3, 10);
        List<String> nomsEsperats = new ArrayList<>();
        nomsEsperats.add("TeclatProva");
        assertEquals(perfilProva.getNomsTeclats(), nomsEsperats);
    }

    @Test
    public void crearTeclatLlistaIdioma() throws Exception {
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
        List<String> nomsEsperats = new ArrayList<>();
        nomsEsperats.add("TeclatProva");
        assertEquals(perfilProva.getNomsTeclats(), nomsEsperats);
    }

    @Test
    //modificar un teclat
    public void modificarTeclat() {

    }

    @Test
    //eliminar un teclat
    public void eliminaTeclat() throws Exception {
        perfilProva.crearTeclatLlistaIdioma("TeclatProva", idiomaProva, 3, 10);
        perfilProva.eliminarTeclat("TeclatProva");
        assertFalse(perfilProva.getNomsTeclats().contains("TeclatProva"));
        assertTrue(perfilProva.getNomsTeclats().isEmpty());
    }

    @Test
    //modificar una llista
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

    @Test
    public void eliminaTeclatInexistent() throws Exception {
        try {
            perfilProva.eliminarTeclat("teclat");
            fail();
        } catch (TeclatNoExisteix e2) {
            assertTrue(true);
        }
    }

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