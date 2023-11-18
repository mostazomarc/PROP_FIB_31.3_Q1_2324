//Driver que ha de provar les funcionalitats principals del programa

package Drivers;
import ControladorsDomini.CtrlDomini;
import Excepcions.*;

import java.util.*;
import java.io.*;

public class DriverDades {
    private CtrlDomini controlador;

    private Scanner s;

    public DriverDades() {
        iniciaDriverDades();
    }

    public void iniciaDriverDades() {
        controlador = controlador.getInstance();
        s = new Scanner(System.in);

    }

    //Pre:
    //Post: Es neteja la terminal
    private void netejaTerminal() {
        System.out.print("\033[H\033[2J");
    }


    //Pre:
    //Post: Es mostra el menu de consultar dades i s'executa la opció escollida
    public void gestionarDades() throws Exception {
        System.out.println("### Gestionar Dades ###");
        printMenuGestionarDades();
        esperarSeleccioGestionarDades();
    }

    //Pre:
    //Post: S'imprimeixen les diferents opcions disponibles de gestionar Dades
    public void printMenuGestionarDades() {
        System.out.println("0. Info de les funcions");
        System.out.println("1. Afegir Llista de Frequencies");
        System.out.println("2. Afegir Alfabet");
        System.out.println("3. Afegir Idioma");
        System.out.println("4. Eliminar Llista de Frequencies");
        System.out.println("5. Eliminar Alfabet");
        System.out.println("6. Eliminar Idioma");
        System.out.println("7. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioGestionarDades() throws Exception {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Gestionar Dades ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Gestionar Dades");
            System.out.println("1. Afegir Llista de Frequencies ---> Afegir una nova llista de frequencies a partir de text/llista o manualment");
            System.out.println("2. Afegir Alfabet ---> Afegir un nou alfabet");
            System.out.println("3. Afegir Idioma ---> Afegir un nou idioma a partir d'un Alfabet i creat amb una llista de frequencies predeterminada");
            System.out.println("4. Eliminar Llista de Frequencies");
            System.out.println("5. Eliminar Alfabet");
            System.out.println("6. Eliminar Idioma");
            System.out.println("7. Sortir ---> Surt de consultar dades");
            esperarSeleccioGestionarDades();
        }
        else if (num== 1) afegirLlistaFrecuencies();
        else if (num == 2) afegirAlfabet();
        else if (num == 3) afegirIdioma();
        else if (num == 4) eliminarLlista();
    }

    //Pre:
    //Post: S'imprimeixen els idiomes disponibles i es retorna l'escollit per l'usuari
    public String selectorIdioma() {
        consultaIdiomes();
        System.out.println("Selecciona l'idioma desitjat escrivint el seu nom:");
        String idioma = s.next();
        return idioma;
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
        Map<String, Integer> novesEntrades = new HashMap<>();

        try {
            String idioma = selectorIdioma();

            if (num == 1 || num == 2) {
                System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
                String filename = s.next();
                if (num == 1) {
                    controlador.novaLlistaPerfil("text", filename, idioma, novesEntrades);
                }
                if (num == 2) {
                    controlador.novaLlistaPerfil("llista", filename, idioma, novesEntrades);
                }
            } else if (num == 3) {
                //llegir manual
                novesEntrades = llistaManual(novesEntrades);
                System.out.println("##### Introduir el nom de la llista: #####");
                String nom = s.next();
                controlador.novaLlistaPerfil("Manual", nom, idioma, novesEntrades);
            }
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
        } //catch IDIOMA NO EXISTEIX
    }

    //Pre:
    //Post: Es llisten les llistes guardades i s'elimina l'indicada per el usuari
    public void eliminarLlista() {
        if (llistarLlistes()) {
            System.out.println("Selecciona la llista a esborrar escrivint el seu nom:");
            String nomLlista = s.next();
            try {
                controlador.eliminarLlista(nomLlista);
            } catch (LlistaFreqNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //Pre:
    //Post: Retorna true si l'String' un numero
    private static boolean esNumero(String paraula) {
        try {
            Double.parseDouble(paraula);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Pre:
    //Post: es llegeixen les entrades manuals per crear una llista de frequencies
    public Map<String,Integer> llistaManual(Map<String, Integer> novesEntrades) {
        System.out.println("##### Introduir freqüències manualment #####");
        System.out.println("##### Introduir paraules i freqüències que es vulguin entrar #####");
        System.out.println("##### Per acabar escriure: 'X' #####");
        while (true) {
            try {
                String paraula = s.next();
                if (paraula.equals("X")) {
                    break;
                }
                Integer freq = s.nextInt();
                if (esNumero(paraula)) {
                    throw new FormatNoValid("Paraula");}
                if (novesEntrades.containsKey(paraula)) {
                    // Si la paraula ja existeix obtenir frequencia actual
                    freq += novesEntrades.get(paraula);
                }
                System.out.println("#### Paraula = " + paraula + ", Freqüencia = " + freq + " ####");
                novesEntrades.put(paraula, freq);
            } catch (FormatNoValid e1) {
                System.out.println("ERROR: La paraula no és una paraula");
            } catch (InputMismatchException e2) {
                System.out.println("ERROR: La freqüència no és un número ");
            }
        }
        if (novesEntrades.isEmpty()) return null;

        return novesEntrades;

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
        System.out.println("2. Alfabets");
        System.out.println("3. Idiomes");
        System.out.println("6. Sortir");
    }

    //Pre:
    //Post: S'espera que l'usuari indiqui la funcionalitat que vol executar i l'executa
    public void esperarSeleccioConsultarDades() {
        System.out.println("Escull una funcionalitat indicant el seu numero corresponent:");
        int num = s.nextInt();
        netejaTerminal();
        if (num== 0) {
            System.out.println("#### Info de les funcions de Consultar Dades ####");
            System.out.println("0. Info de les funcions ---> Explicació de les funcions de Consultar Dades");
            System.out.println("1. Llistes de Frequencies ---> Es llisten les llistes guardades del perfil i es pot consultar el seu contingut");
            System.out.println("2. Alfabets ---> Es llisten els alfabets guardats del sistema i les seves lletres");
            System.out.println("3. Idioma ---> Es llisten els diferents idiomes guardats al sistema amb els seus respectius alfabets");
            System.out.println("6. Sortir ---> Surt de consultar dades");
            esperarSeleccioConsultarDades();
        }
        else if(num == 1) llistarLlistes();
        else if (num == 2) consultaAlfabets();
        else if (num == 3) consultaIdiomes();
        else if (num == 6) {}

    }

    //Pre:
    //Post: Es llisten els noms de les llistes guardades del perfil actiu
    public boolean llistarLlistes() {
        List<String> nomLlistes = controlador.getNomLlistesGuardades();
        netejaTerminal();
        if (nomLlistes.isEmpty()) {
            System.out.println("No hi ha llistes guardades");
            return false;
        }
        else {
            try {
                int i = 1;
                for (String nom : nomLlistes) {
                    String idioma = controlador.getNomIdiomaLlista(nom);
                    System.out.println(i + ": " + nom + " Idioma: " + idioma);
                    ++i;
                }
                System.out.println("Si vol consultar una llista en detall entri el numero, si no entri '0':");
                int num = s.nextInt();
                if (num != 0) {
                    netejaTerminal();
                    if (nomLlistes.size() < num) throw new LlistaFreqNoExisteix();
                    String nomSeleccio = nomLlistes.get(num - 1);
                    Map<String, Integer> llista = controlador.consultaLlista(nomSeleccio);
                    System.out.println("Paraula: , Frequencia: ");
                    for (Map.Entry<String, Integer> entry : llista.entrySet()) {
                        String clave = entry.getKey();
                        Integer valor = entry.getValue();
                        System.out.println(clave + ", " + valor);
                    }

                }
            } catch (LlistaFreqNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return true;
    }

    public void consultaIdiomes() {
        System.out.println("### Consultar Idiomes ###");
        Vector<String> dades = controlador.consultaIdiomes();
        mostraDadesIdiomes(dades);
    }

    public void  consultaAlfabets() {
        System.out.println("### Consultar Alfabets ###");
        Vector<String> dades = controlador.consultaAlfabets();
        mostraDadesIdiomes(dades);
    }

    public void afegirAlfabet() throws Exception {
        System.out.println("### Afegir Alfabet ###");
        System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
        String filename = s.next();
        try {
            controlador.afegirAlfabet(filename);
        }
        catch (AlfabetJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        catch (FormatNoValid e2) {
            System.out.println("ERROR: " + e2.getMessage());
        }
    }

    public void afegirIdioma() throws Exception {
        System.out.println("### Afegir Idioma ###");
        System.out.println("Introdueixi el nom de l'Idioma: ");
        String nomIdioma = s.next();
        System.out.println("Introdueixi el nom de l'Alfabet que té l'idioma: ");
        String nomAlfabet = s.next();
        System.out.println("Introdueixi el nom de l'Arxiu que conté la llista de Frequències predeterminada de l'idioma: ");
        String filename = s.next();
        System.out.println("Introdueixi qui tipus d'arxiu 'text' o 'llista': ");
        String tipusArxiu = s.next();
        try {
            controlador.afegirIdioma(nomIdioma, nomAlfabet, tipusArxiu, filename);
        }
        catch (IdiomaJaExisteix e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (AlfabetNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        }
        catch (FormatNoValid e2) {
            System.out.println("ERROR: " + e2.getMessage());
        }
    }

    public void mostraDadesIdiomes(Vector<String> dades) {
        int n = dades.size();
        for (int i=0; i < n; ++i) System.out.println(dades.get(i));
    }


    public static void main(String[] args) {
        DriverDades dd = new DriverDades();
        System.out.println("Estas provant el driver de les dades \n");
        dd.iniciaDriverDades();
    }
}