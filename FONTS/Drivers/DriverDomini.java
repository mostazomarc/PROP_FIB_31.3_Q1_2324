//Driver que ha de provar les funcionalitats principals del programa

package Drivers;
import ControladorsDomini.CtrlDomini;

import java.util.*;
import java.io.*;

public class DriverDomini {
    private CtrlDomini controlador;

    public void iniciaDriverDomini() {
        controlador = controlador.getInstance();
        controlador.iniciaInstancia("Prova");
        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuració actual del programa es la següent:");
        System.out.println("## Perfil: " + controlador.getPerfilActual());
        System.out.println("## Estratègia: " + controlador.getEstrategiaActual());
        System.out.println("\nEl driver disposa de les següents funcions:\n");
        printMenu();
        //repinstruccions();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles del driver
    public void printMenu() {
        System.out.println("0. ");
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("5. ");
        System.out.println("6. Sortir");
    }

    public static void main(String[] args) {
        DriverDomini dd = new DriverDomini();
        System.out.println("Estas provant el driver del controlador de la capa domini\n");
        dd.iniciaDriverDomini();
    }
}