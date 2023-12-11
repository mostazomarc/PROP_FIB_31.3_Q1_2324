package JUnit;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlFile;
import Domini.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  InputFrequenciesTest {

    CtrlDomini cDomini = CtrlDomini.getInstance();

    Map<String, Integer> novesEntradesLlegidesAPart = new HashMap<>();

    @Before
    public void setUpBeforeClass() throws Exception {
        System.out.println("Comencen els tests de InputFrequencies");
        cDomini.iniciaInstancia("PerfilProva");
        cDomini.afegirAlfabet("./DATA/alfabetCatala.txt");
        cDomini.afegirIdioma("Català", "AlfabetCatala" , "llista", "./DATA/catalaFreq.txt");
    }

    @Before
    public  void llegirArxiuAPart() throws Exception {
        CtrlFile prova = CtrlFile.getInstance();
        List<String> LlistaLlegida = new ArrayList<>();
        try {
            LlistaLlegida = prova.llegirArxiu("./DATA/catalaFreq.txt");
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        }

        for (String linia : LlistaLlegida) {
            String[] parella = linia.split(" ");
            int n = parella.length;
            if (n - 2 >= 0) {
                String paraula = parella[n - 2];
                Integer frequencia = Integer.parseInt(parella[n - 1]);
                //System.out.println(parella[n-2]+ " " + parella[n-1]);

                if (novesEntradesLlegidesAPart.containsKey(paraula)) {
                    // Si la paraula ja existeix obtenir frequencia actual
                    int FrecVella = novesEntradesLlegidesAPart.get(paraula);
                    // Sumar la nova vella frequencia a la nova
                    frequencia += FrecVella;
                }

                novesEntradesLlegidesAPart.put(paraula, frequencia);
            }
        }
    }

    private void imprimirLlista(Map<String, Integer> llista) {
        for (Map.Entry<String, Integer> entry : llista.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void testInputFrequencies() throws Exception {
        Map<String, Integer> novesEntrades = new HashMap<String, Integer>();
        cDomini.novaLlistaPerfil("llista", "./DATA/catalaFreq.txt", "Català", novesEntrades);
        Map<String, Integer > result = cDomini.consultaLlista("catalaFreq.txt");
        imprimirLlista(result);
        assertEquals(novesEntradesLlegidesAPart, result);
    }

}