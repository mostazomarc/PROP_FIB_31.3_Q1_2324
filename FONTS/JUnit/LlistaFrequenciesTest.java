package JUnit;

import Domini.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class LlistaFrequenciesTest {
    @Test
    public void creadora() {
        LlistaFrequencies llistaEsperada = new LlistaFrequencies("NovaLlista");
        LlistaFrequencies llistaProva = new LlistaFrequencies("novallista");
    }
}
