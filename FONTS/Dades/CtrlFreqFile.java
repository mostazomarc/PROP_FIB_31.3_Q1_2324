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
    //Post: Retorna totes les paraules del fitxer en un map de paraules i frequencies
    public Map<String, Integer> llegirArxiuFreq(String filename) throws FileNotFoundException {
        Map<String,Integer> LlistaEntrada = new HashMap<>();

        //suposant que el directori d'execució es subgrup
        FileReader fr = new FileReader("./DATA/"+filename);
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            String paraula = new String(scan.next());
            Integer num = Integer.parseInt(scan.next());
            LlistaEntrada.put(paraula,num);
        }
        return LlistaEntrada;
    }
}