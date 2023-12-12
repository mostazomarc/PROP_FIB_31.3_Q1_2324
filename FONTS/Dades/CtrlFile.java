package Dades;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * CtrlFile es una classe que permet llegir fitxers de text i retorna les seves linies en una llista
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la lectura de fitxers de text</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlFile {
    /**
     * Instància de CtrlFile
     */
    private static CtrlFile singletonObject;

    /**
     * Creadora de CtrlFile
     */
    private CtrlFile() {
    }

    /**
     * Retorna la instancia de CtrlFile, si no existeix cap CtrlFile es crea.
     * @return La instancia de CtrlFile
     */
    public static CtrlFile getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFile() {

            };
        return singletonObject;
    }

    /**
     * Llegeix un fitxer de text i retorna les seves linies en una llista
     * @param filepath El path del fitxer
     * @return Una llista amb les linies del fitxer
     * @throws Exception Si el fitxer no existeix
     */

    public List<String> llegirArxiu(String filepath) throws Exception {
        LinkedList<String> linies = new LinkedList<String>();

        //suposant que el directori d'execució es subgrup
        FileReader fr = new FileReader(filepath);

        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) linies.add(scan.nextLine());
        scan.close();
        return linies;
    }
}
