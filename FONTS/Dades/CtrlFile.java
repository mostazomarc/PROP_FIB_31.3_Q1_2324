package Dades;

import java.io.*;
import java.util.*;

public class CtrlFile {
    private static CtrlFile singletonObject;


    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlFile getInstance(){
        if(singletonObject == null)
            singletonObject = new CtrlFile(){

            };
        return singletonObject;
    }

    private CtrlFile() {}

    //Pre: filename es el nom d'un arxiu que existeix
    //Post: Retorna totes les linies del fitxer en una llista
    public List<String> llegirArxiu(String filename) throws Exception  {
        LinkedList<String> linies = new LinkedList<String>();

        //suposant que el directori d'execució es subgrup
        FileReader fr = new FileReader("./DATA/" + filename);

        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) linies.add(new String(scan.nextLine()));
        scan.close();
        return linies;
    }
}

//Classe Programada per: Marc