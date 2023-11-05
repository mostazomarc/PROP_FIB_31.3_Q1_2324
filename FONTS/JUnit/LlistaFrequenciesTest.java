package JUnit;

import Domini.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class LlistaFrequenciesTest {
    @Test
    public void creadoraLlistaPerfil() {
        LlistaFrequencies llistaEsperada = new LlistaFrequencies("NovaLlista");
        Perfil perfilProva = new Perfil("Prova");
        LlistaFrequencies resultat = perfilProva.crearLlistaFreq("NovaLlista");
        assertEquals(llistaEsperada.getNom(),resultat.getNom());
    }
}
