package Presentacio;

import java.io.*;
import java.util.*;

public class VistaTerminal {
    private ControladorPresentacio ctrlP;
    private Scanner s;
    public VistaTerminal(ControladorPresentacio cp) {
        ctrlP = cp;
        s = new Scanner(System.in);
    };

    //Pre:
    //Post: S'inicialitza la terminal
    public void inicialitzaTerminal() {
        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuracio actual del programa es la seguent:");
        System.out.println("## Perfil: " + ctrlP.getPerfilActual());
        System.out.println("## Estrategia: " + ctrlP.getEstrategiaActual());

        System.out.println("\nEl programa disposa de les seguents funcions:\n");
        printMenu();
        repinstruccions();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles del programa
    public void printMenu() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Gestionar teclats");
        System.out.println("2. Consultar teclats");
        System.out.println("3. Gestionar dades");
        System.out.println("4. Consultar dades");
        System.out.println("5. Canviar estrategia");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void repinstruccions() {
        System.out.println("Escolleig una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        System.out.println("Seleccionat:");
        if (num== 0) System.out.println("0. Info de les funcions");
        else if (num== 1) System.out.println("1. Gestionar teclats");
        else if (num== 2) System.out.println("2. Consultar teclats");
        else if (num== 3) System.out.println("3. Gestionar dades");
        else if (num== 4) System.out.println("4. Consultar dades");
        else if (num== 5) System.out.println("5. Canviar estrategia");
    }
}