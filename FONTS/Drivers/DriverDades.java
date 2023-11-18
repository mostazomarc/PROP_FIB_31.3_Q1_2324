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
        System.out.println("2. Modificar Llista de Frequencies");
        System.out.println("3. Afegir Alfabet");
        System.out.println("4. Afegir Idioma");
        System.out.println("5. Eliminar Llista de Frequencies");
        System.out.println("6. Eliminar Alfabet");
        System.out.println("7. Eliminar Idioma");
        System.out.println("8. Sortir");
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
            System.out.println("2. Modificar Llista de Frequencies ---> Sobrescriure les dades d'una llista de frequencies");
            System.out.println("3. Afegir Alfabet ---> Afegir un nou alfabet");
            System.out.println("4. Afegir Idioma ---> Afegir un nou idioma a partir d'un Alfabet i creat amb una llista de frequencies predeterminada");
            System.out.println("5. Eliminar Llista de Frequencies");
            System.out.println("6. Eliminar Alfabet");
            System.out.println("7. Eliminar Idioma");
            System.out.println("8. Sortir ---> Surt de consultar dades");
            esperarSeleccioGestionarDades();
        }
        else if (num== 1) afegirLlistaFrecuencies();
        else if (num == 2) modificarLlistaFrequencies();
        else if (num == 3) afegirAlfabet();
        else if (num == 4) afegirIdioma();
        else if (num == 5) eliminarLlista();
        else if (num == 6) eliminarAlfabet();
        else if (num == 7) eliminarIdioma();
    }

    //Pre:
    //Post: Es modifica una llista existen sobrescrivint la llista amb la nova informació obtinguda
    public void modificarLlistaFrequencies() throws Exception{
        System.out.println("#### Modificar Llista ####");
        llistarLlistes();
        System.out.println("Introdueixi el nom de la llista a modificar o X per cancelar");
        String nomllista = s.next();
        if (nomllista.equals("X")) return;
        System.out.println("Selecciona el tipus d'arxiu:");
        System.out.println("1. Text");
        System.out.println("2. Llista");
        System.out.println("3. Manual");
        int num = s.nextInt();

        try {
            String nomArxiu = "";
            if (num ==1 || num == 2) {
                System.out.println("Introdueixi el nom de l'arxiu que vol llegir o X per cancelar");
                nomArxiu = s.next();
            }
            if (num == 1) controlador.modificarLlistaPerfil("text", nomArxiu, nomllista, new HashMap<>());
            else if (num == 2) controlador.modificarLlistaPerfil("llista", nomArxiu, nomllista, new HashMap<>());
            else if (num == 3) {
                Map<String, Integer> novesEntrades = new HashMap<>();
                novesEntrades = llistaManual(novesEntrades);
                controlador.modificarLlistaPerfil("Manual", nomArxiu, nomllista, novesEntrades);
            }
            System.out.println("Llista " + nomllista + " modificada correctament");
        } catch (LlistaFreqNoExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (FormatNoValid e2) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        }
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
    public void afegirLlistaFrecuencies() throws Exception {
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

            if (num == 1 || num == 2) {
                String idioma = selectorIdioma();
                System.out.println("Introdueixi el nom de l'arxiu i aseguri's de que es a la carpeta DATA");
                String filename = s.next();
                if (num == 1) {
                    controlador.novaLlistaPerfil("text", filename, idioma, novesEntrades);
                    System.out.println("Afegida la llista a partir de text amb nom: " +filename+ " i idioma: " + idioma);
                }
                if (num == 2) {
                    controlador.novaLlistaPerfil("llista", filename, idioma, novesEntrades);
                    System.out.println("Afegida la llista a partir d'una llista amb nom: " +filename+ " i idioma: " + idioma);
                }
            } else if (num == 3) {
                //llegir manual
                String idioma = selectorIdioma();
                novesEntrades = llistaManual(novesEntrades);
                System.out.println("##### Introduir el nom de la llista: #####");
                String nom = s.next();
                controlador.novaLlistaPerfil("Manual", nom, idioma, novesEntrades);
                System.out.println("Afegida manualment la llista amb nom: " +nom+ " i idioma: " + idioma);
            }
        } catch (LlistaFreqJaExisteix e1) {
            System.out.println("ERROR: " + e1.getMessage());
        } catch (IdiomaNoExisteix e2) {
            System.out.println("ERROR: " + e2.getMessage());
        } catch (FormatNoValid e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (LletraNoInclosa e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (LlistaBuida e4) {
            System.out.println("ERROR: " + e4.getMessage());
        }
    }

    //Pre:
    //Post: Es llisten les llistes guardades i s'elimina l'indicada per el usuari
    public void eliminarLlista()throws Exception {
        if (llistarLlistes()) {
            System.out.println("Selecciona la llista a esborrar escrivint el seu nom o X per cancelar:");
            String nomLlista = s.next();
            if (nomLlista.equals("X")) return;
            try {
                controlador.eliminarLlista(nomLlista);
                System.out.println("Eliminada la llista amb nom: " + nomLlista);
            } catch (LlistaFreqNoExisteix e1) {
                System.out.println("ERROR: " + e1.getMessage());
            } catch (LlistaFreqEnUs e2 ) {
                System.out.println("ERROR: " + e2.getMessage());
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
    public Map<String,Integer> llistaManual(Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat{
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
        if (novesEntrades.isEmpty()) throw new FormatNoValid("LLISTA NO BUIDA");

        return novesEntrades;

    }

    //Pre:
    //Post: Es mostra el menu de consultar dades i s'executa la opció escollida
    public void consultarDades() throws Exception {
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
    public void esperarSeleccioConsultarDades() throws Exception {
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
    public boolean llistarLlistes() throws Exception {
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
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Has d'entrar un número");
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
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        }
    }

    public void eliminarAlfabet() throws Exception {
        System.out.println("### Eliminar Alfabet ###");
        System.out.println("Introdueixi el nom de l'Alfabet: ");
        String nomAlfabet = s.next();
        try {
            controlador.eliminarAlfabet(nomAlfabet);
        }
        catch (AlfabetNoExisteix e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (AlfabetEnUs e1) {
            System.out.println("ERROR: " + e1.getMessage());
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
        } catch (FileNotFoundException e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (LletraNoInclosa e3) {
            System.out.println("ERROR: " + e3.getMessage());
        } catch (LlistaBuida e4) {
            System.out.println("ERROR: " + e4.getMessage());
        }
    }

    public void eliminarIdioma() throws Exception {
        System.out.println("### Eliminar Idioma ###");
        System.out.println("Introdueixi el nom de l'Idioma: ");
        String nomIdioma = s.next();
        try {
            controlador.eliminarIdioma(nomIdioma);
        }
        catch (IdiomaNoExisteix e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (AlfabetEnUs e1) {
            System.out.println("ERROR: " + e1.getMessage());
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