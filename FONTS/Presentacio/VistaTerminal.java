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

    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
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
        netejaTerminal();
        if (num== 0) System.out.println("0. Info de les funcions");
        else if (num== 1) System.out.println("1. Gestionar teclats");
        else if (num== 2) System.out.println("2. Consultar teclats");
        else if (num== 3) gestionarDades();
        else if (num== 4) System.out.println("4. Consultar dades");
        else if (num== 5) System.out.println("5. Canviar estrategia");
    }

    public void gestionarDades() {
        System.out.println("### Gestionar Dades ###");
        printMenuGestionarDades();
        esperarSeleccioGestionarDades();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles de gestionar Dades
    public void printMenuGestionarDades() {
        System.out.println("0. Afegir Llista de Frecuencies");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioGestionarDades() {
        System.out.println("Escolleig una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        System.out.println("Seleccionat:");
        netejaTerminal();
        if (num== 0) afegirLlistaFrecuencies();
    }

    //Pre:
    //Post:
    public void afegirLlistaFrecuencies() {
        System.out.println("### Afegir Llista Frequencies ###");
        System.out.println("Com la vols afegir?");
        System.out.println("1. Text");
        System.out.println("2. Llista");
        System.out.println("3. Entrada manual");
        int num = s.nextInt();
        netejaTerminal();
        if (num == 1 || num == 2) {
            System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
            String filename = s.next();
                if (num == 1) {
                    ctrlP.llegirLlistaFreq("text", filename);
                }
                if (num == 2) {
                    ctrlP.llegirLlistaFreq("llista", filename);
                }
        }
        else if (num== 3) {
            //llegir manual
        }
    }
}