package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.LlistaFreqNoExisteix;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class PerfilTest {
    Perfil perfilProva;
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

    @Before
    public void crearPerfilProva() {
        perfilProva = new Perfil("Prova","12345");
    }

    /* NO DEIXA PROVAR PERQUE ES PRIVADA
    @Test
    //Creadora Perfil amb usuari i contrasenya i getUsuari i getContrasenya
    public void comprovaLlistaNoExisteix() {
        Perfil perfilResultat = new Perfil("Prova","12345");
        try {
            perfilResultat.comprovaLlistaNoExisteix("llista");
            assertEquals(true,false); //si arribem aqui no ha saltat l'excepció
        }   catch (LlistaFreqNoExisteix e1) {
            assertEquals(true,true);
        }
    }

     */


    @Test
    //Creadora Perfil amb usuari i contrasenya i getUsuari i getContrasenya
    public void creadoraPerfilContrasenya() {
        Perfil perfilResultat = new Perfil("Prova","12345");
        assertEquals("Prova",perfilResultat.getUsuari());
        assertEquals("12345",perfilResultat.getContrasenya());
    }

    @Test
    //Creadora Perfil amb usuari i getNom
    public void creadoraPerfil() {
        Perfil perfilResultat = new Perfil("Prova");
        assertEquals("Prova",perfilResultat.getUsuari());
    }

    @Test
    //GetUsuari
    public void getUsuari() {
        Perfil perfilResultat = new Perfil("Prova");
        assertEquals("Prova",perfilResultat.getUsuari());
    }

    @Test
    //GetContrasenya
    public void getContrasenya() {
        Perfil perfilResultat = new Perfil("Prova","12345");
        assertEquals("12345",perfilResultat.getContrasenya());
    }

    @Test
    //canvia usuari
    public void canviaUsuari() {
        perfilProva.canviaUsuari("newProva");
        assertEquals("newProva",perfilProva.getUsuari());
        assertNotEquals("Prova",perfilProva.getUsuari());
    }

    @Test
    //canvia contrasenya
    public void canviaContrasenya() {
        perfilProva.canviaContrasenya("newContrasenya");
        assertEquals("newContrasenya",perfilProva.getContrasenya());
        assertNotEquals("12345",perfilProva.getContrasenya());
    }

    @Test
    //afegir llista freq amb nom i llista de paraules
    public void afegirLlistaFreq() throws ExcepcionsCreadorTeclat{
        perfilProva.afegirLlistaFreq("LlistaProva",idiomaProva,llistaParaulesProva);
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms,perfilProva.getNomAllLlistes());
        assertEquals(llistaParaulesProva,perfilProva.consultaLlista("LlistaProva"));
    }

    @Test
    //afegir llista nomes amb nom
    public void crearLlistaFreq() {
        try {
            perfilProva.crearLlistaFreq("LlistaProva", idiomaProva);
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (Exception e2) {
            System.out.println("ERROR");
        }
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms,perfilProva.getNomAllLlistes());
    }

    @Test
    //eliminar llista amb nom
    public void eliminarLlistaFreq() {
        try {
            perfilProva.crearLlistaFreq("LlistaProva", idiomaProva);
            perfilProva.eliminaLlista("LlistaProva");
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (Exception e2) {
            System.out.println("ERROR");
        }
        assertTrue(perfilProva.getNomAllLlistes().isEmpty());
    }

    @Test
    //get el nomes de totes les llistes del perfil
    public void getNomAllLlistes() {
        try {
            perfilProva.crearLlistaFreq("LlistaProva",idiomaProva);
            perfilProva.crearLlistaFreq("LlistaProva3",idiomaProva);
            perfilProva.crearLlistaFreq("LlistaProva4",idiomaProva);
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (Exception e2) {
            System.out.println("ERROR");
        }
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        llistaNoms.add("LlistaProva3");
        llistaNoms.add("LlistaProva4");
        List<String> llistaResultat = perfilProva.getNomAllLlistes();
        Collections.sort(llistaNoms);
        Collections.sort(llistaResultat);
        assertEquals(llistaNoms,llistaResultat);
    }

    @Test
    //obtenir la llista de paraules i frequencies
    public void getNomIdiomaLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq("LlistaProva",idiomaProva,llistaParaulesProva);
        assertEquals("ESPAÑOL",perfilProva.getNomIdiomaLlista("LlistaProva"));
    }

    @Test
    //obtenir la llista de paraules i frequencies
    public void consultarLlista() throws ExcepcionsCreadorTeclat {
        perfilProva.afegirLlistaFreq("LlistaProva",idiomaProva,llistaParaulesProva);
        Map<String,Integer> resultat = perfilProva.consultaLlista("LlistaProva");
        assertEquals(resultat,llistaParaulesProva);
    }

    @Test
    //obtenir la llista de paraules i frequencies
    public void crearTeclat() {

    }
}

//Classe Programada per: Marc