package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class PerfilTest {
    Perfil perfilProva;
    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

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
    public void afegirLlistaFreq() {
        perfilProva.afegirLlistaFreq("LlistaProva",llistaParaulesProva);
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms,perfilProva.getNomAllLlistes());
        assertEquals(llistaParaulesProva,perfilProva.consultaLlista("LlistaProva"));
    }

    @Test
    //afegir llista nomes amb nom
    public void crearLlistaFreq() {
        perfilProva.crearLlistaFreq("LlistaProva");
        List<String> llistaNoms = new ArrayList<>();
        llistaNoms.add("LlistaProva");
        assertEquals(llistaNoms,perfilProva.getNomAllLlistes());
    }

    @Test
    //get el nomes de totes les llistes del perfil
    public void getNomAllLlistes() {
        perfilProva.crearLlistaFreq("LlistaProva");
        perfilProva.crearLlistaFreq("LlistaProva3");
        perfilProva.crearLlistaFreq("LlistaProva4");
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
    public void consultarLlista() {
        perfilProva.afegirLlistaFreq("LlistaProva",llistaParaulesProva);
        Map<String,Integer> resultat = perfilProva.consultaLlista("LlistaProva");
        assertEquals(resultat,llistaParaulesProva);
    }
}