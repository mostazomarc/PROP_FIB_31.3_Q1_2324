package JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import JUnit.*;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = {
        AlfabetTest.class, FactoriaTest.class, IdiomaTest.class, LlistaFrequenciesTest.class, PerfilTest.class, TeclatTest.class
})
public class MasterTest {}