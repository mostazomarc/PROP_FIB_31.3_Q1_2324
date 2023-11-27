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
/**
 * MasterTestSuite es una classe que ens permet testejar tots els tests JUnit
 * @author Marc Mostazo Gonzalez (marc.mostazo@estudiantat.upc.edu)
 */
public class MasterTestSuite {
}

//Classe Programada per: Marc