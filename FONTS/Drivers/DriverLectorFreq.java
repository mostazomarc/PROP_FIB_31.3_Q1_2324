package Drivers;
import Dades.CtrlFile;

import java.util.*;
import java.io.*;

public class DriverLectorFreq {
    private CtrlFile prova;

    public void llegirArxiuFreq() throws Exception {
        prova = prova.getInstance();
        System.out.println("Llegint arxiu LlistatFrequencies.txt\n");
        List<String> LlistaFrequencies = new ArrayList<>();
        try {
            LlistaFrequencies = prova.llegirArxiu("LlistatFrequencies.txt");
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        }

        for (String linia : LlistaFrequencies) {
            String[] parella = linia.split(" ");
            System.out.println(parella[0] + " " + parella[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        DriverLectorFreq dl = new DriverLectorFreq();
        System.out.println("Estas provant el driver de la classe CtrlFreqFile\n");
        dl.llegirArxiuFreq();
    }
}

//Classe Programada per: Marc