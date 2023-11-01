package Drivers;
import Dades.CtrlFreqFile;

import java.util.*;
import java.io.*;

public class DriverLectorFreq {
    private CtrlFreqFile prova;

    public void llegirArxiuFreq() throws FileNotFoundException {
        prova = prova.getInstance();
        System.out.println("Llegint arxiu LlistatFrequencies.txt\n");
        Map<String,Integer> LlistaParaules = prova.llegirArxiuFreq("LlistatFrequencies.txt");

        // Itera sobre el map i imprimeix la paraula i la frequencia
        for (Map.Entry<String, Integer> entry : LlistaParaules.entrySet()) {
            String paraula = entry.getKey();
            Integer freq = entry.getValue();
            System.out.println("Paraula: " + paraula + ", Frecuencia: " + freq);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        DriverLectorFreq dl = new DriverLectorFreq();
        System.out.println("Estas provant el driver de la classe CtrlFreqFile\n");
        dl.llegirArxiuFreq();
    }
}