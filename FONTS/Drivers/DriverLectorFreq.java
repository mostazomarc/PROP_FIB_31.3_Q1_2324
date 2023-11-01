package Drivers;

import java.util.Scanner;

public class DriverLectorFreq {
    private CtrlFreqFile prova;

    public void llegirArxiuFreq() {
        Scanner sc = new Scanner(System.in);
        String nomArxiu = sc.nextLine();
        prova = new CtrlFreqFile();
        prova.llegirArxiuFreq(nomArxiu);
    }

    public static void main(String[] args) {
        DriverLectorFreq = new DriverLectorFreq();
        System.out.println("Estas provant el driver de la classe CtrlFreqFile\n");
    }
}