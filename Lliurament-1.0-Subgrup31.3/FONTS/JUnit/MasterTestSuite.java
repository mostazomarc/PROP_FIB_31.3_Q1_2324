package JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = {
        AlfabetTest.class, BranchandBoundTest.class, CtrlPersAlfabetsTest.class, CtrlPersFreqTest.class, CtrlPersIdiomesTest.class, CtrlPersIdiomesTest.class, CtrlPersPerfilTest.class,
        CtrlPersTeclatsTest.class, GreedyTest.class, HungarianAlgorithmTest.class, IdiomaTest.class, LlistaFrequenciesTest.class, NodoTest.class, PerfilTest.class, posTest.class, TeclatTest.class
})
public class MasterTestSuite {
}

//Classe Programada per: Marc