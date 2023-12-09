/*
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
    //Post: Es neteja la terminal
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
            System.out.println("####################################");
            System.out.print("Presioni Enter per continuar...\n");
            s.nextLine();
            s.nextLine();
            netejaTerminal();
            System.out.print("Vol fer algo més?\n");
            printMenu();
            repinstruccions();
        }
    }

    //Pre:
    //Post: Es mostra el menu de consultar dades i s'executa la opció escollida
    public void gestionarDades() {
        System.out.println("### Gestionar Dades ###");
        printMenuGestionarDades();
        esperarSeleccioGestionarDades();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles de gestionar Dades
    public void printMenuGestionarDades() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Afegir Llista de Frecuencies");
        System.out.println("2. Afegir Alfabet");
        System.out.println("3. Afegir Idioma");
        System.out.println("6. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioGestionarDades() {
        System.out.println("Escolleig una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) System.out.println("0. Info de les funcions");
        else if (num== 1) afegirLlistaFrecuencies();
        else if (num == 2) afegirAlfabet();
        else if (num == 3) afegirIdioma();
    }

    //Pre:
    //Post: Es mostren les diferents opcions per afegir una llista i s'afegeix de la manera seleccionada
    public void afegirLlistaFrecuencies() {
        System.out.println("### Afegir Llista Frequencies ###");
        System.out.println("Com la vols afegir?");
        System.out.println("1. Text");
        System.out.println("2. Llista");
        System.out.println("3. Entrada manual");
        System.out.println("6. Sortir");
        int num = s.nextInt();
        netejaTerminal();
        if (num == 1 || num == 2) {
            System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
            String filename = s.next();
            System.out.println("Introdueixi el nom de l'IDIOMA");
            //Llistar Idiomes existents
            String idioma = s.next();
                if (num == 1) {
                    ctrlP.llegirLlistaFreq("text", filename,idioma);
                }
                if (num == 2) {
                    ctrlP.llegirLlistaFreq("llista", filename,idioma);
                }
        }
        else if (num== 3) {
            //llegir manual
        }
    }

    //Pre:
    //Post: Es mostra el menu de consultar dades i s'executa la opció escollida
    public void consultarDades() {
        System.out.println("### Consultar Dades ###");
        printMenuConsultarDades();
        esperarSeleccioConsultarDades();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles de consultar Dades
    public void printMenuConsultarDades() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Llistes de Frecuencies");
        System.out.println("2. Idiomes amb alfabet associat i num de lletres de l'alfabet");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioConsultarDades() {
        System.out.println("Escolleig una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) System.out.println("0. Info de les funcions");
        else if(num == 1) llistarLlistes();
        else if (num == 2) ctrlP.consultaIdiomes();
    }

    //Pre:
    //Post: Es llisten els noms de les llistes guardades del perfil actiu
    public void llistarLlistes() throws Exception {
        List<String> nomLlistes = ctrlP.getNomLlistesGuardades();
        netejaTerminal();
        if (nomLlistes.isEmpty()) System.out.println("No n'hi han llistes guardades");
        else {
            int i = 1;
            for (String nom : nomLlistes) {
                System.out.println(i + ": " + nom);
                ++i;
            }
            System.out.println("Si vol consultar una llista en detall entri el numero, si no entri '0':");
            int num = s.nextInt();
            if (num != 0) {
                netejaTerminal();
                if (nomLlistes.size() < num) System.out.println("ERROR");
                else {
                    String nomSeleccio = nomLlistes.get(num - 1);
                    Map<String, Integer> llista = ctrlP.consultaLlista(nomSeleccio);
                    //per provar
                    for (Map.Entry<String, Integer> entry : llista.entrySet()) {
                        String clave = entry.getKey();
                        Integer valor = entry.getValue();
                        System.out.println("Paraula: " + clave + ", Frequencia: " + valor);
                    }
                }
            }
        }

    }

    public void afegirAlfabet() {
        System.out.println("### Afegir Alfabet ###");
        System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
        String filename = s.next();
        ctrlP.afegirAlfabet(filename);
    }

    public void afegirIdioma() {
        System.out.println("### Afegir Idioma ###");
        System.out.println("Introdueixi el nom de l'Idioma: ");
        String nomIdioma = s.next();
        System.out.println("Introdueixi el nom de l'Alfabet que té l'idioma: ");
        String nomAlfabet = s.next();
        System.out.println("Introdueixi el nom de l'Arxiu que conté la llista de Frequències predeterminada de l'idioma: ");
        String filename = s.next();
        System.out.println("Introdueixi qui tipus d'arxiu 'text' o 'llista': ");
        String tipusArxiu = s.next();
        ctrlP.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
    }

    public void mostraDadesIdiomes(Vector<String> dades) {
        int n = dades.size();
        for (int i=0; i < n; ++i) System.out.println(dades.get(i));
    }

    public void mostrarMissatge(String m) {
        System.out.println(m);
    }

}

 */