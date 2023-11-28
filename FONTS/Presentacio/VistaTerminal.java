
package Presentacio;

import Excepcions.FormatNoValid;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;
import Excepcions.CapPerfilGuardat;

import java.io.*;
import java.util.*;

public class VistaTerminal {
    private ControladorPresentacio controlador;
    private VistaTerminalTeclats vTeclats;
    private VistaTerminalDades vDades;
    private Scanner s;
    public VistaTerminal(ControladorPresentacio cp) {
        controlador = cp;
        s = new Scanner(System.in);
    };

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }

    public void inicialitzaTerminal() throws Exception {
        vDades = new VistaTerminalDades(controlador);
        vTeclats = new VistaTerminalTeclats(controlador);
        try {
            llistarPerfils(false);
            System.out.println("Entra el nom del perfil amb el que vols entrar ");
            String nom = s.next();
            controlador.iniciaInstancia(nom);
            controlador.carregarDades();
            System.out.println("Inicia sessió: " + "Prova");
        } catch (PerfilJaExisteix e1 ) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (PerfilNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (FormatNoValid e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (CapPerfilGuardat e){
            System.out.println("CAP PERFIL REGISTRAT");
            System.out.println("Entra el nom del perfil  a crear: ");
            String nom = s.next();
            controlador.iniciaInstancia(nom);
            controlador.carregarDades();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s = new Scanner(System.in);

        System.out.println("\n ##################### BENVINGUT AL SISTEMA CREADOR DE TECLATS ##################### \n");
        System.out.println("\nLa configuració actual del programa per fer proves es la següent:");
        System.out.println("## Perfil: " + controlador.getPerfilActual());
        System.out.println("## Estratègia: " + controlador.getEstrategiaActual());
        System.out.println("## Alfabet: alfabetCatala, alfabetEspañol, LlatíGenèric");
        System.out.println("## Idioma: Català, Español");
        System.out.println("## Llista Predeterminada Català: LlistaPredCatalà");
        System.out.println("## Llista Predeterminada Español: LlistaPredEspañol");

        System.out.println("\nEl driver disposa de les següents funcions:\n");
        printMenu();
        repinstruccions();
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
        else if (num== 1) vTeclats.gestionarTeclats();
        else if (num== 2) vTeclats.consultarTeclats();
        else if (num== 3) vDades.gestionarDades();
        else if (num== 4) vDades.consultarDades();
        else if (num== 5) System.out.println("5. Canviar estrategia");
        else if (num== 6) {
            canviarPerfil();
        } else if (num == 7) {
            controlador.guardaEstat();
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
        llistarPerfils(true);
        System.out.println("### Selecciona un Perfil o crea un nou escrivint el nom d'aquest o sortir per cancelar ###");
        System.out.println("### WARNING: Aquesta versió del program careix de capa de persistencia i per tant no podrà crear llistes/teclats amb el mateix nom que un altre usuari!!!!!! ###");
        String nomPerfil = s.next();
        List<String> nomsPerfils = controlador.getAllPerfils();
        try {
            if (nomPerfil.toLowerCase().equals("sortir")){}
            else if (nomsPerfils.contains(nomPerfil)) {
                controlador.iniciaInstancia(nomPerfil);
                System.out.println("Inicia sessió: " + nomPerfil);
            }
            else {
                System.out.println("Es crearà el Perfil: " + nomPerfil);
                System.out.println("Està segur? Si/No");
                String resposta = s.next();
                if (resposta.equals("Si") || resposta.equals("si")) {
                    controlador.iniciaInstancia(nomPerfil);
                    System.out.println("Creat Perfil: " + nomPerfil);
                    System.out.println("Inicia sessió: " + nomPerfil);
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

    public void llistarPerfils(boolean inicialitzat) {
        List<String> nomsPerfils = controlador.getAllPerfils();
        System.out.println("Perfils al sistema: \n");
        for (String nom : nomsPerfils) {
            System.out.println("Perfil: " + nom);
        }

        if (inicialitzat) System.out.println("\nPerfil Actual: " + controlador.getPerfilActual());
    }
}