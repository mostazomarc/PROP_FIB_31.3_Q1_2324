package Presentacio;

import java.io.*;
import java.util.*;

public class VistaTerminal {
    private ControladorPresentacio ctrlP;
    private scanner s;
    public VistaTerminal(ControladorPresentacio cp) {
        ctrlP = cp;
        s = new Scanner(System.in);
    };

    public void inicialitzaTerminal() {
        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuracio actual del programa es la seguent:");
    }
}