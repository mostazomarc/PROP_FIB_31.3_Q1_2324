package Drivers;
import Dades.CtrlFile;

import java.util.*;
import java.io.*;

public class DriverLectorFreq {
    private CtrlFile prova;

    public void llegirArxiuFreq() throws Exception {
        prova = prova.getInstance();
        System.out.println("Llegint arxiu LlistatFrequencies.txt\n");
        List<String> LlistaLlegida = new ArrayList<>();
        try {
            LlistaLlegida = prova.llegirArxiu("./DATA/catalaFreq.txt");
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        }

        Map<String, Integer> novesEntrades = new HashMap<>();
        for (String linia : LlistaLlegida) {
            String[] parella = linia.split(" ");
            int n = parella.length;
            if (n - 2 >= 0) {
                String paraula = parella[n - 2];
                Integer frequencia = Integer.parseInt(parella[n - 1]);
                //System.out.println(parella[n-2]+ " " + parella[n-1]);

                if (novesEntrades.containsKey(paraula)) {
                    // Si la paraula ja existeix obtenir frequencia actual
                    int FrecVella = novesEntrades.get(paraula);
                    // Sumar la nova vella frequencia a la nova
                    frequencia += FrecVella;
                }

                novesEntrades.put(paraula, frequencia);
            }
        }

        for (Map.Entry<String, Integer> entry : novesEntrades.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        DriverLectorFreq dl = new DriverLectorFreq();
        System.out.println("Estas provant el driver de la classe CtrlFreqFile\n");
        dl.llegirArxiuFreq();
    }
}

//Classe Programada per: Marc