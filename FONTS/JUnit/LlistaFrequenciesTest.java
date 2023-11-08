package JUnit;

import Domini.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LlistaFrequenciesTest {
    private Map<String, Integer> llistaParaulesProva = new HashMap<>();

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
        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista");
        assertEquals("NovaLlista",resultat.getNom());
    }

    @Test
    //Creadora LLista amb nom i Llista de paraules i getFrequencies
    public void creadoraLlistaNomLlista() {

        LlistaFrequencies resultat = new LlistaFrequencies("NovaLlista", llistaParaulesProva);
        assertEquals("NovaLlista",resultat.getNom());
        assertEquals(llistaParaulesProva,resultat.getFrequencies());
    }

    @Test
    //Insertar llista de frequencies a una llista ja creada
    public void insertarFrequencies() {

        LlistaFrequencies llistaProva = new LlistaFrequencies("NovaLlista");
        llistaProva.insertarFrequencies(llistaParaulesProva);
        assertEquals(llistaParaulesProva,llistaProva.getFrequencies());
    }


}
