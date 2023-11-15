package Drivers;
import Dades.CtrlFile;

import java.util.*;
import java.io.*;

public class DriverLectorFreq {
    private CtrlFile prova;

    public void llegirArxiuFreq() throws FileNotFoundException {
        prova = prova.getInstance();
        System.out.println("Llegint arxiu LlistatFrequencies.txt\n");
        List<String> LlistaFrequencies = prova.llegirArxiu("LlistatFrequencies.txt");

        for (String linia : LlistaFrequencies) {
            String[] parella = linia.split(" ");
            System.out.println(parella[0] + " " + parella[1]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        DriverLectorFreq dl = new DriverLectorFreq();
        System.out.println("Estas provant el driver de la classe CtrlFreqFile\n");
        dl.llegirArxiuFreq();
    }
}

//Classe Programada per: Marc