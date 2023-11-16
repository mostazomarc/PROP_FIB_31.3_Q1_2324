//Driver que ha de provar les funcionalitats principals del programa

package Drivers;
import ControladorsDomini.CtrlDomini;
import Excepcions.*;

import java.util.*;
import java.io.*;

public class DriverTeclats {
    private CtrlDomini controlador;

    private Scanner s;

    public DriverTeclats() {
        iniciaDriverTeclats();
    }

    public void iniciaDriverTeclats() {
        controlador = controlador.getInstance();
        s = new Scanner(System.in);

    }

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }


    //Pre:
    //Post: Es mostra el menu consultar teclats i s'executa l'opció escollida
    public void gestionarTeclats() {
        System.out.println("### Gestionar Teclats ###");
        printMenuGestionarTeclats();
        esperarSeleccioGestionarTeclats();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles de gestionar Teclats
    public void printMenuGestionarTeclats() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Crear Teclat amb llista pròpia");
        System.out.println("2. Crear Teclat sense llista pròpia");
        System.out.println("3. Modificar Teclat");
        System.out.println("4. Eliminar Teclat");
        System.out.println("5. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioGestionarTeclats() {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Gestionar Dades ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Gestionar Teclats");
            System.out.println("1. Crear Teclat amb llista pròpia ---> Afegir un nou Teclat a partir d'un idioma i llista de freqüències");
            System.out.println("2. Crear Teclat sense llista pròpia ---> Afegir un nou Teclat a partir de l'idioma");
            System.out.println("3. Modificar Teclat ---> Modificar el layout del Teclat");
            System.out.println("4. Eliminar Teclat ---> Eliminar teclat");
            System.out.println("5. Sortir ---> Surt de la gestió de teclats");
            esperarSeleccioGestionarTeclats();
        }
        else if (num== 1) crearTeclatLlistaPropia();
        //else if (num == 2) crearTeclatSenseLlistaPropia();
        //else if (num == 2) modificarTeclat();
        //else if (num == 3) eliminarTeclat();
        //sortir implementar?
    }


    //Pre:
    //Post:
    public void crearTeclatLlistaPropia() {
        System.out.println("### Crear Teclat ###");
        System.out.println("Introdueixi el nom del Teclat: ");
        String nomTeclat= s.next();
        System.out.println("Introdueixi el nom de l'idioma que vol utilitzar: ");
        String nomIdioma = s.next();
        System.out.println("Introdueixi el nom de la llista de frequències que vol utilitzar: ");
        String nomLlistaFreq = s.next();
        try {
            controlador.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq);
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (IdiomaNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarTeclats() throws Exception {
        System.out.println("### Consultar Teclats ###");
        printMenuConsultarTeclats();
        esperarSeleccioConsultarTeclats();
    }

    public void printMenuConsultarTeclats() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Llistar Teclats");
        System.out.println("2. Buscar Teclat");
        System.out.println("3. Sortir");
    }

    public void esperarSeleccioConsultarTeclats() throws Exception {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Consultar Teclats ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Consultar Teclats");
            System.out.println("1. Llistar Teclats ---> Es llisten els Teclats creats");
            System.out.println("2. Buscar Teclat ---> S'imprimeix el Teclat a partir del seu nom");
            System.out.println("6. Sortir ---> Surt de consultar teclats");
            esperarSeleccioConsultarTeclats();
        }
        else if(num == 1) llistarTeclats();
//        else if (num == 2) buscarTeclat();
        else if (num == 6) {}
    }


    public void llistarTeclats() throws Exception {
        System.out.println("### Llistar Teclats ###");
        List<String> nomTeclats = controlador.getNomsTeclats();
        netejaTerminal();
        if (nomTeclats.isEmpty()) {
            System.out.println("No hi ha teclats guardats");
        }
        else {
            try {
                int i = 1;
                for (String nomt : nomTeclats) {
                    String idioma = controlador.getNomIdiomaTeclat(nomt);
                    String llistafreq = controlador.getNomLListaTeclat(nomt);
                    System.out.println(i + "Nom: " + nomt + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq);
                    ++i;
                }
            }
            catch(TeclatNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            }
        }
    }



    public static void main(String[] args) {
        DriverTeclats dt = new DriverTeclats();
        System.out.println("Estas provant el driver del controlador de la capa domini\n");
        dt.iniciaDriverTeclats();
    }
}