
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
        System.out.println("## Perfil: " );
        System.out.println("## Estrategia: ");

        System.out.println("\nEl programa disposa de les seguents funcions:\n");
        //printMenu();
        //repinstruccions();
    }

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }

}