package JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = {
        AlfabetTest.class, IdiomaTest.class, LlistaFrequenciesTest.class, PerfilTest.class, CtrlPersPerfilTest.class, CtrlPersFreqTest.class, TeclatTest.class, CtrlPersTeclatsTest.class, HungarianAlgorithmTest.class
})
public class MasterTestSuite {
}

//Classe Programada per: Marc