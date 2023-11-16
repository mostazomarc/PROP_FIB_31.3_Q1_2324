//Driver que ha de provar les funcionalitats principals del programa

package Drivers;
import ControladorsDomini.CtrlDomini;
import Excepcions.*;

import java.util.*;
import java.io.*;

public class DriverDominiv2 {
    private CtrlDomini controlador;
    private DriverDades dDades;
    private DriverTeclats dTeclats;

    private Scanner s;

    public void iniciaDriverDomini() throws Exception {
        controlador = controlador.getInstance();
        dTeclats = new DriverTeclats();
        dDades = new DriverDades();
        s = new Scanner(System.in);

        try {
            controlador.iniciaInstancia("Prova");
            controlador.afegirAlfabet("Llatí.txt");
            controlador.afegirIdioma("Català","llatí","llista","catalaFreq.txt");
            Map<String, Integer> novesEntrades = new HashMap<>();
            controlador.novaLlistaPerfil("llista","catalaFreq.txt", "Català", novesEntrades);
        } catch (PerfilJaExisteix e1 ) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (PerfilNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (FormatNoValid e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuració actual del programa per fer proves es la següent:");
        System.out.println("## Perfil: " + controlador.getPerfilActual());
        System.out.println("## Estratègia: " + controlador.getEstrategiaActual());
        System.out.println("## Alfabet: Llatí");
        System.out.println("## Idioma: Català");
        System.out.println("## Llista Predeterminada Català: catalaFreq.txt");

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
        System.out.println("6. Canviar Perfil");
        System.out.println("7. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void repinstruccions() throws Exception {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Consultar Dades ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions del programa");
            System.out.println("1. Gestionar teclats ---> Entra en el menú gestionar teclats que permet gestionar els teclats");
            System.out.println("2. Consultar teclats ---> Entra en el menú consultar teclats que permet consultar els teclats");
            System.out.println("3. Gestionar dades ---> Entra en el menú gestionar dades que permet gestionar les dades del sistema i el perfil");
            System.out.println("4. Consultar dades ---> Entra en el menú consultar dades que permet consultar les dades del sistema i el perfil");
            System.out.println("5. Canviar estratègia ---> Es canvia d'estratègia per confeccionar teclats");
            System.out.println("6. Sortir ---> Surt de consultar dades");
        }
        else if (num== 1) dTeclats.gestionarTeclats();
        else if (num== 2) dTeclats.consultarTeclats();
        else if (num== 3) dDades.gestionarDades();
        else if (num== 4) dDades.consultarDades();
        else if (num== 5) System.out.println("5. Canviar estrategia");
        else if (num== 6) {
            canviarPerfil();
        }
        if (num!= 7) {
            System.out.println("################Presioni Enter per continuar...####################");
            s.nextLine();
            s.nextLine();
            netejaTerminal();
            System.out.println("Vol fer algo més?\n");
            printMenu();
            repinstruccions();
        }
    }

    //Pre:
    //Post: Es canvia al perfil que s'especifiqui i si no existeix es crea
    public void canviarPerfil() {
        llistarPerfils();
        System.out.println("### Selecciona un Perfil o crea un nou escrivint el nom d'aquest###");
        String nomPerfil = s.next();
        List<String> nomsPerfils = controlador.getAllPerfils();
        try {
            if (nomsPerfils.contains(nomPerfil)) controlador.iniciaInstancia(nomPerfil);
            else {
                System.out.println("Es crearà el Perfil: " + nomPerfil);
                System.out.println("Està segur? Si/No");
                String resposta = s.next();
                if (resposta.equals("Si") || resposta.equals("si")) {
                    controlador.iniciaInstancia(nomPerfil);
                    System.out.println("Creat Perfil: " + nomPerfil);
                }
                else System.out.println("No s'han fet canvis");
            }
        } catch (PerfilJaExisteix e1 ) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (PerfilNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void llistarPerfils() {
        List<String> nomsPerfils = controlador.getAllPerfils();
        System.out.println("Perfils al sistema: \n");
        for (String nom : nomsPerfils) {
            System.out.println("Perfil: " + nom);
        }

        System.out.println("\nPerfil Actual: " + controlador.getPerfilActual());
    }

    public static void main(String[] args) throws Exception{
        DriverDominiv2 dd = new DriverDominiv2();
        System.out.println("Estas provant el driver del controlador de la capa domini\n");
        dd.iniciaDriverDomini();
    }
}