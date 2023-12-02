package Dades;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CtrlFile {
    private static CtrlFile singletonObject;


    private CtrlFile() {
    }

    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlFile getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFile() {

            };
        return singletonObject;
    }

    //Pre: filename es el nom d'un arxiu que existeix
    //Post: Retorna totes les linies del fitxer en una llista
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

//Classe Programada per: Marc