package JUnit;

import Domini.*;
import Domini.LlistaFrequencies;
import Stubs.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TeclatTest {

    private char[][] teclatQWERTY = {
            {'q', 'w', 'e','r', 't', 'y','u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'k' , 'l', 'ñ'},
            {'z', 'x', 'c','v','b','n','m'}
    };

    @Test
    public void crearTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals("nom",resultat.getNomTeclat());
        assertEquals(teclatQWERTY,resultat.getTeclat());
    }

    @Test
    public void obtenirDisposicióTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals(teclatQWERTY,resultat.getTeclat());
    }

    @Test
    public void obtenirNomTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat resultat = new Teclat("nom",llista,idioma);
        assertEquals("nom",resultat.getNomTeclat());
    }

    @Test
    public void canviarTeclat() {
        LlistaFrequenciesStub llista = new LlistaFrequenciesStub();
        IdiomaStub idioma = new IdiomaStub();
        Teclat prova = new Teclat("nom",llista,idioma);
        assertNotNull(prova.canviaDisposicioTeclat());
    }
}