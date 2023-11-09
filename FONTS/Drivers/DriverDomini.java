//Driver que ha de provar les funcionalitats principals del programa

package Drivers;
import ControladorsDomini.CtrlDomini;

import java.util.*;
import java.io.*;

public class DriverDomini {
    private CtrlDomini controlador;

    private Scanner s;

    public void iniciaDriverDomini() {
        controlador = controlador.getInstance();
        s = new Scanner(System.in);

        controlador.iniciaInstancia("Prova");
        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuració actual del programa es la següent:");
        System.out.println("## Perfil: " + controlador.getPerfilActual());
        System.out.println("## Estratègia: " + controlador.getEstrategiaActual());

        System.out.println("\nEl driver disposa de les següents funcions:\n");
        printMenu();
        repinstruccions();
    }

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles del driver
    public void printMenu() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Gestionar teclats");
        System.out.println("2. Consultar teclats");
        System.out.println("3. Gestionar dades");
        System.out.println("4. Consultar dades");
        System.out.println("5. Canviar estrategia");
        System.out.println("6. Sortir");
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
        else if (num== 4) consultarDades();
        else if (num== 5) System.out.println("5. Canviar estrategia");
        if (num!= 6) {
            System.out.println("################\"Presioni Enter per continuar...\\n\"####################");
            s.nextLine();
            s.nextLine();
            netejaTerminal();
            System.out.print("Vol fer algo més?\n");
            printMenu();
            repinstruccions();
        }
    }

    public void gestionarDades() {}

    public void consultarDades() {}

    public static void main(String[] args) {
        DriverDomini dd = new DriverDomini();
        System.out.println("Estas provant el driver del controlador de la capa domini\n");
        dd.iniciaDriverDomini();
    }
}