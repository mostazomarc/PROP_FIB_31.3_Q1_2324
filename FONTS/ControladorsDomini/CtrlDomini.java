package ControladorsDomini;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Domini.*;
import Dades.*;
import Excepcions.*;

public class CtrlDomini {
    private Perfil PerfilActual; //Perfil que esta usant actualment el programa
    private String Estrategia; //Estrategia utilitzada en la fabricació del teclat
    private CtrlPersPerfil perfils; //Controlador Persistencia Perfils registrats

    private CtrlPersFreq llistes;
    private static CtrlDomini singletonObject;
    private CtrlFile ctrlFreqFile;

    private TreeMap<String, Alfabet> Alfabets;
    private TreeMap<String, Idioma> Idiomes;

    //Pre:
    //Post: Es crea una instancia de domini.
    public CtrlDomini(){
        inicialitzar();
    }

    //Pre:
    //Post: Retorna la instancia de Controlador Domini. Si no existeix cap instancia de CtrlDomini, es crea.
    public static CtrlDomini getInstance(){ //CtrlDomini es singleton
        if(singletonObject == null){
            singletonObject = new CtrlDomini();
        }
        return singletonObject;
    }

    //Pre:
    //Post: S'inicialitzen les variables necessaries.
    public void inicialitzar() {
        ctrlFreqFile = CtrlFile.getInstance();
        perfils = CtrlPersPerfil.getInstance();
        llistes = CtrlPersFreq.getInstance();
        Estrategia = "BranchAndBound"; //estrategia per defecte
        Alfabets = new TreeMap<String, Alfabet>();
        Idiomes = new TreeMap<String, Idioma>();
    }

    //Pre: Es rep un nom d'usuari
    //Post: S'inicia instancia amb l'usuari rebut, si no existeix es crea
    public void iniciaInstancia(String nom) throws ExcepcionsCreadorTeclat{
        System.out.println("inicia sessio: " + nom +"\n");
        try {
            PerfilActual = perfils.getPerfil(nom);
            llistes.canviaPerfil(nom);
        } catch (PerfilNoExisteix e1) {
            PerfilActual = perfils.afegirPerfil(nom);
            llistes.nouPerfil(nom);
        }
    }


    //Pre:
    //Post: Retorna el conjunt de noms dels perfils.
    public List<String> getAllPerfils() {
        return perfils.getAllPerfils();
    }

    //Pre:
    //Post: Retorna la instancia del Perfil Actual.
    public String getPerfilActual() {
        return PerfilActual.getUsuari();
    }

    //Pre:
    //Post: Retorna el nom de l'estrategia per fer la distribució de teclat
    public String getEstrategiaActual() {
        return Estrategia;
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

    public String getNomIdiomaTeclat(String nomt) throws ExcepcionsCreadorTeclat{
        String idioma = PerfilActual.getIdiomaTeclat(nomt);
        return idioma;
    }

    public String getNomLListaTeclat(String nomt) throws ExcepcionsCreadorTeclat{
        String nomllistafreq = PerfilActual.getLlistaTeclat(nomt);
        return nomllistafreq;
    }

    //Pre: LlistaLlegida es una llista de frequencies llegida en format vàlid
    //Post: es passa la llista llegida a llista de frequencies
    private Map<String,Integer> llistaToEntrades(Map<String, Integer> novesEntrades, List<String> LlistaLlegida) throws FormatNoValid{
        try {
            for (String linia : LlistaLlegida) {
                String[] parella = linia.split(" ");
                int n = parella.length;
                if (n - 2 >= 0) {
                    String paraula = parella[n - 2];
                    Integer frequencia = Integer.parseInt(parella[n - 1]);
                    //System.out.println(parella[n-2]+ " " + parella[n-1]);

                    if (novesEntrades.containsKey(paraula)) {
                        // Si la paraula ja existeix obtenir frequencia actual
                        int FrecVella = novesEntrades.get(paraula);
                        // Sumar la nova vella frequencia a la nova
                        frequencia += FrecVella;
                    }

                    novesEntrades.put(paraula, frequencia);
                }
            }
        } catch (NumberFormatException e) {
            throw new FormatNoValid("Llista");
        }
        return novesEntrades;
    }

    //Pre: La llista llegida es un text que es vol passar a frequencies
    //Post: Es passa la llista de string llegits a les entrades de llista de frequencies
    private Map<String,Integer> textToEntrades(Map<String, Integer> novesEntrades, List<String> LlistaLlegida) {
        Pattern patron = Pattern.compile("\\p{L}+");

        for (String linea : LlistaLlegida) {
            Matcher matcher = patron.matcher(linea);

            while (matcher.find()) {
                String paraula = matcher.group();
                int freq = 1;
                if (!esNumero(paraula)) { //aixi no afegim numeros a la llista de freqüencies
                    if (novesEntrades.containsKey(paraula)) {
                        // Si la paraula ja existeix obtenir frequencia actual
                        freq += novesEntrades.get(paraula);
                    }

                    novesEntrades.put(paraula, freq);
                }
            }
        }
        return novesEntrades;
    }


    //Pre: tipus arxiu es un tipus vàlid i filename existeix i esta en un format vàid
    //Post: Es llegeix l'informacio de llista de l'arxiu i es retorna
    public Map<String,Integer> llegirLlistaFreq(String tipusArxiu, String filename) throws FormatNoValid {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiu(filename);
        Map<String, Integer> novesEntrades = new HashMap<>();


        if (tipusArxiu == "llista") {
            novesEntrades = llistaToEntrades(novesEntrades,LlistaLlegida);
        }
         else {
            novesEntrades = textToEntrades(novesEntrades,LlistaLlegida);
        }
        return novesEntrades;
    }

    //Pre: tipus arxiu es un tipus vàlid i filename existeix i esta en un format vàid
    //Post: S'afegeix la informació de l'arxiu de llista de frequencies filename al Perfil Actual
    public void novaLlistaPerfil(String tipusArxiu, String filename, String i , Map<String,Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        if (tipusArxiu != "Manual") novesEntrades = llegirLlistaFreq(tipusArxiu,filename);
        PerfilActual.afegirLlistaFreq(filename,Idiomes.get(i),novesEntrades);
    }

    public List<String> getNomsTeclats() { return PerfilActual.getNomsTeclats();}

    //Pre:
    //Post: S'obté un set dels noms de les llistes guardades del perfil actual
    public List<String> getNomLlistesGuardades() {
        return PerfilActual.getNomAllLlistes();
    }

    //Pre: La llista amb nom nomLlista existeix
    //Post: Es retorna el nom de l'idioma de la llista amb nom nomllista
    public String getNomIdiomaLlista(String nomllista) throws ExcepcionsCreadorTeclat{
        return PerfilActual.getNomIdiomaLlista(nomllista);
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws ExcepcionsCreadorTeclat {
        return PerfilActual.consultaLlista(nomSeleccio);
    }

    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        PerfilActual.eliminaLlista(nomLlista);
    }

    public void afegirAlfabet(String filename) throws ExcepcionsCreadorTeclat {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiu(filename);

        Set<Character> lletres = new HashSet<Character>();
        String nomAlfabet = filename.substring(0, filename.length() - 4);

        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) throw new AlfabetJaExisteix(nomAlfabet);

        for (String linia : LlistaLlegida) {
            for (char lletra : linia.toCharArray()) {
                lletres.add(lletra);
            }
        }

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, lletres);
        Alfabets.put(nomAlfabet.toLowerCase(), nouAlfabet);
    }

    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filename) throws ExcepcionsCreadorTeclat {
        if (Idiomes.containsKey(nomIdioma)) throw new IdiomaJaExisteix(nomIdioma);
        else if (!Alfabets.containsKey(nomAlfabet.toLowerCase())) throw new AlfabetNoExisteix(nomAlfabet);

        Alfabet alfabetIdioma = Alfabets.get(nomAlfabet);
        Map<String, Integer> novesEntrades = llegirLlistaFreq(tipusArxiu, filename);
        Idioma nouIdioma = new Idioma(nomIdioma, alfabetIdioma, filename, novesEntrades);
        Idiomes.put(nomIdioma, nouIdioma);
    }

    public Vector<String> consultaIdiomes() {
        Vector<String> sdades = new Vector<String>();

        int i = 1;
        for (Map.Entry<String, Idioma> idioma : Idiomes.entrySet()) {
            Idioma id = idioma.getValue();
            sdades.add(i + ". " + id.getInfo());
            ++i;
        }

        return sdades;
    }
    public void crearTeclat(String nomTeclat, String nomIdioma, String nomLlistaFreq) throws ExcepcionsCreadorTeclat{
        if (!Idiomes.containsKey(nomIdioma)) throw new IdiomaNoExisteix(nomIdioma);
        Idioma idiomaTeclat = Idiomes.get(nomIdioma);
        PerfilActual.crearTeclat(nomTeclat, nomLlistaFreq, idiomaTeclat);
    }

    public void llistarTeclats() {
        //PerfilActual.llistarTeclats();
    }

    public Vector<String> consultaAlfabets() {
        Vector<String> sdades = new Vector<String>();

        int i = 1;
        for (Map.Entry<String, Alfabet> alfabet : Alfabets.entrySet()) {
            Alfabet a = alfabet.getValue();
            sdades.add(i +". " + a.getInfo());
            ++i;
        }

        return sdades;
    }

}