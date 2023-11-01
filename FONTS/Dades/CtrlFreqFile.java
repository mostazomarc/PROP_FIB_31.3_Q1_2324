package Dades;

import java.io.*;
import java.util.*;

public class CtrlFreqFile {
    private static CtrlFreqFile singletonObject;


    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlFreqFile getInstance(){
        if(singletonObject == null)
            singletonObject = new CtrlFreqFile(){

            };
        return singletonObject;
    }

    private CtrlFreqFile() {}

    //Pre: filename es el nom d'un arxiu que existeix
    //Post: Retorna totes les linies del fitxer en una llista
    public List<String> llegirArxiuFreq(String filename)  {
        LinkedList<String> linies = new LinkedList<String>();

        try {//suposant que el directori d'execució es subgrup
            FileReader fr = new FileReader("./DATA/" + filename);

            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) linies.add(new String(scan.nextLine()));
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error al obrir l'arxiu: " + e.getMessage());
        }
        return linies;
    }
}