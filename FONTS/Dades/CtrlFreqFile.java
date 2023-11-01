package Dades;

import java.io*;
import java.util.*;

public class CtrlFreqFile {
    private statis CtrlFreqFile singletonObject;


    //Pre:
    //Post: Retorna la instancia de CtrlFreqFile, si no existeix cap CtrlFreqFile es crea.
    public static CtrlFreqFile getInstance(){
        if(singletonObject == null)
            singletonObject = new CtrlFreqFile(){

            };
        return singletonObject;
    }

    private CtrlFreqFile() {}

    public Map<String, Integer> llegirArxiuFreq(String filename) throws FileNotFoundException {
        Map<String,Integer> LlistaParaules = new HashMap<>();

        FileReader fr = new FileReader("../../DATA/"+filename);
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) System.out.println(new String(Scan.nextLine()));
        scan.close();
        return LlistaParaules;
    }
}