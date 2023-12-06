//Driver que ha de provar les funcionalitats principals del programa

package Presentacio;
import Excepcions.*;

import java.util.*;
import java.io.*;

public class VistaTerminalTeclats {
    private ControladorPresentacio controlador;

    private Scanner s;

    public VistaTerminalTeclats(ControladorPresentacio c) {
        controlador = c;
        iniciaDriverTeclats();
    }

    public void iniciaDriverTeclats() {
        s = new Scanner(System.in);

    }

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }


    //Pre:
    //Post: Es mostra el menu consultar teclats i s'executa l'opció escollida
    public void gestionarTeclats() throws Exception{
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
        System.out.println("3. Modificar Layout Teclat");
        System.out.println("4. Eliminar Teclat");
        System.out.println("5. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioGestionarTeclats() throws Exception{
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Gestionar Dades ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Gestionar Teclats");
            System.out.println("1. Crear Teclat amb llista pròpia ---> Afegir un nou Teclat a partir d'un idioma i llista de freqüències");
            System.out.println("2. Crear Teclat sense llista pròpia ---> Afegir un nou Teclat a partir de l'idioma");
            System.out.println("3. Modificar Layout Teclat ---> Modificar el layout del Teclat");
            System.out.println("4. Eliminar Teclat ---> Eliminar teclat");
            System.out.println("5. Sortir ---> Surt de la gestió de teclats");
            esperarSeleccioGestionarTeclats();
        }
        else if (num== 1) crearTeclatLlistaPropia();
        else if (num == 2) crearTeclatSenseLlistaPropia();
        else if (num == 3) modificarLayoutTeclat();
        else if (num == 4) eliminarTeclat();
        //sortir implementar?
    }

    public void modificarLayoutTeclat() throws Exception {
        if (!controlador.getNomsTeclats().isEmpty()) {
            System.out.println("### Modificar Layout Teclat ###");
            System.out.println("Aquests són els teclats que tens actualment: ");
            List<String> nomTeclats = controlador.getNomsTeclats();
            int i = 1;
            for (String nomt : nomTeclats) {
                String idioma = controlador.getNomIdiomaTeclat(nomt);
                String llistafreq = controlador.getNomLListaTeclat(nomt);
                System.out.println();
                System.out.println(i + ". Nom: " + nomt + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq);
                System.out.println();
                ++i;
            }
            System.out.println("Introdueix el número del teclat a modificar");
            int num = s.nextInt();
            try {
                if (num != 0) {
                    netejaTerminal();
                    if (nomTeclats.size() < num) throw new TeclatNoExisteix();
                    String nomSeleccio = nomTeclats.get(num - 1);
                    String idioma = controlador.getNomIdiomaTeclat(nomSeleccio);
                    String llistafreq = controlador.getNomLListaTeclat(nomSeleccio);
                    System.out.println(num + ". Nom: " + nomSeleccio + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq + "\n");
                    char[][] teclat = controlador.consultaTeclat(nomSeleccio);
                    System.out.println("Disposicio: ");
                    for (int k = 0; k < teclat.length; ++k) {
                        for (int j = 0; j < teclat[0].length; ++j) {
                            System.out.print("[" + teclat[k][j] + "]");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("El layout del teclat ara mateix es de " + teclat.length + " files x " + teclat[0].length + " columnes");
                    System.out.println("Introdueix el nou nombre de files:");
                    int numf = s.nextInt();
                    System.out.println("Introdueix el nou nombre de columnes:");
                    int numc = s.nextInt();
                    controlador.modificarLayoutTeclat(nomSeleccio, numf, numc);
                }
            } catch (TeclatNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            } catch (LayoutNoValid e2) {
                System.out.println("ERROR: " + e2.getMessage());
            } catch (LayoutMassaGran e3) {
                System.out.println("ERROR: " + e3.getMessage());
            }
            System.out.println();
        }
        else System.out.println("No hi ha teclats guardats");
    }

    //Pre:
    //Post:
    public void crearTeclatLlistaPropia() throws Exception{
        System.out.println("### Crear Teclat Llista Pròpia ###");
        System.out.println("Introdueixi el nom del Teclat: ");
        String nomTeclat= s.next();
        System.out.println("Introdueixi el nom de l'idioma que vol utilitzar: ");
        String nomIdioma = s.next();
        System.out.println("Introdueixi el nom de la llista de frequències que vol utilitzar: ");
        String nomLlistaFreq = s.next();
        System.out.println("Introdueixi el nombre de files del layout: ");
        int n = s.nextInt();
        System.out.println("Introdueixi el nombre de columnes del layout: ");
        int m = s.nextInt();
        try {
            controlador.crearTeclatLlistaPropia(nomTeclat, nomIdioma, nomLlistaFreq, n, m, "BranchAndBound");
            char[][] teclat = controlador.consultaTeclat(nomTeclat);
            System.out.println();
            System.out.println("La disposicio del teclat generat ha estat la següent: \n");
            for (int k=0; k<teclat.length; ++k) {
                for (int j=0; j<teclat[0].length; ++j) {
                    if (teclat[k][j] != ' ') {
                        System.out.print("[" + teclat[k][j] + "]");
                    }
                }
                System.out.println();
            }

        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (IdiomaNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (LayoutNoValid e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (IdiomesDiferents e4) {
            System.out.println("ERROR: " + e4.getMessage());
        } catch (TeclatJaExisteix e5) {
            System.out.println("ERROR: " + e5.getMessage());
        } catch (LayoutMassaGran e6) {
            System.out.println("ERROR: " + e6.getMessage());
        }

    }

    public void crearTeclatSenseLlistaPropia() throws Exception{
        System.out.println("### Crear Teclat Llista Pròpia ###");
        System.out.println("Introdueixi el nom del Teclat: ");
        String nomTeclat= s.next();
        System.out.println("Introdueixi el nom de l'idioma que vol utilitzar: ");
        String nomIdioma = s.next();
        System.out.println("Introdueixi el nombre de files del layout: ");
        int n = s.nextInt();
        System.out.println("Introdueixi el nombre de columnes del layout: ");
        int m = s.nextInt();
        try {
            controlador.crearTeclatLlistaIdioma(nomTeclat, nomIdioma, n, m, "BranchAndBound");
            char[][] teclat = controlador.consultaTeclat(nomTeclat);
            System.out.println();
            System.out.println("La disposició del teclat generat ha estat la següent: \n");
            for (int k=0; k<teclat.length; ++k) {
                for (int j=0; j<teclat[0].length; ++j) {
                    if (teclat[k][j] != ' ') {
                        System.out.print("[" + teclat[k][j] + "]");
                    }
                }
                System.out.println();
            }
            System.out.println();
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (IdiomaNoExisteix e2 ) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (LayoutNoValid e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (TeclatJaExisteix e5) {
            System.out.println("ERROR: " + e5.getMessage());
        } catch (LayoutMassaGran e6) {
            System.out.println("ERROR: " + e6.getMessage());
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
        System.out.println("2. Sortir");
    }

    public void esperarSeleccioConsultarTeclats() throws Exception {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Consultar Teclats ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Consultar Teclats");
            System.out.println("1. Llistar Teclats ---> Es llisten els Teclats creats");
            System.out.println("6. Sortir ---> Surt de consultar teclats");
            esperarSeleccioConsultarTeclats();
        }
        else if(num == 1)  llistarTeclats();
    }


    public boolean llistarTeclats() throws Exception {
        List<String> nomTeclats = controlador.getNomsTeclats();
        netejaTerminal();
        if (nomTeclats.isEmpty()) {
            System.out.println("No hi ha teclats guardats");
            return false;
        }
        else {
            System.out.println("### Llistar Teclats ###");
            try {
                int i = 1;
                for (String nomt : nomTeclats) {
                    String idioma = controlador.getNomIdiomaTeclat(nomt);
                    String llistafreq = controlador.getNomLListaTeclat(nomt);
                    System.out.println();
                    System.out.println(i + ". Nom: " + nomt + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq);
                    System.out.println();
                    ++i;
                }
                System.out.println("Si vol consultar un teclat en detall entri el numero, si no entri '0':");
                int num = s.nextInt();
                if (num != 0) {
                    netejaTerminal();
                    if (nomTeclats.size() < num) throw new TeclatNoExisteix();
                    String nomSeleccio = nomTeclats.get(num - 1);
                    String idioma = controlador.getNomIdiomaTeclat(nomSeleccio);
                    String llistafreq = controlador.getNomLListaTeclat(nomSeleccio);
                    System.out.println(num + ". Nom: " + nomSeleccio + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq + "\n" );
                    char[][] teclat = controlador.consultaTeclat(nomSeleccio);
                    System.out.println("Disposició: ");
                    for (int k=0; k<teclat.length; ++k) {
                        for (int j=0; j<teclat[0].length; ++j) {
                            if (teclat[k][j] != ' ') {
                                System.out.print("[" + teclat[k][j] + "]");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("El layout és de " + teclat.length + " files per " + teclat[0].length + " columnes\n");
                }
            } catch(TeclatNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            }
        }
        return true;
    }

    public void eliminarTeclat() throws Exception {
        List<String> nomTeclats = controlador.getNomsTeclats();
        netejaTerminal();
        if (nomTeclats.isEmpty()) {
            System.out.println("No hi ha teclats guardats");
        } else {
            try {
                int i = 1;
                for (String nomt : nomTeclats) {
                    String idioma = controlador.getNomIdiomaTeclat(nomt);
                    String llistafreq = controlador.getNomLListaTeclat(nomt);
                    System.out.println();
                    System.out.println(i + ". Nom: " + nomt + " Idioma: " + idioma + " Llista de Freqüències: " + llistafreq);
                    System.out.println();
                    ++i;
                }
                System.out.println("Si vol eliminar un teclat entri el número, si no entri '0':");
                int num = s.nextInt();
                if (num != 0) {
                    netejaTerminal();
                    if (nomTeclats.size() < num) throw new TeclatNoExisteix();
                    String nomSeleccio = nomTeclats.get(num - 1);
                    controlador.eliminarTeclat(nomSeleccio);
                    System.out.println("Eliminat teclat amb nom: " + nomSeleccio);
                }
            } catch (TeclatNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            }
            System.out.println();
        }
    }
}