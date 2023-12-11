package ControladorsDomini;

import java.security.PrivilegedExceptionAction;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Domini.*;
import Dades.*;
import Excepcions.*;

/**
 * CtrlDomini es una classe que actua de controlador de les classes de domini
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la comunicació entre la capa de presentació/Dades i la capa de domini</p>
 */
public class CtrlDomini {
    /**
     * Perfil que esta usant actualment el programa
     */
    private Perfil PerfilActual;
    /**
     * Estrategia utilitzada en la fabricació del teclat
     */
    private String Estrategia;
    /**
     * Controlador de Persistencia de Perfils registrats
     */
    private CtrlPersPerfil perfils;
    /**
     * Controlador de Persistencia de Llistes de Frequencies
     */
    private CtrlPersFreq llistes;
    /**
     * Controlador de Persistencia d'Alfabets
     */
    private CtrlPersAlfabets alfabets;
    /**
     * Controlador de Persistencia d'Idiomes
     */
    private CtrlPersIdiomes idiomes;
    /**
     * Controlador de Persistencia d'Idiomes
     */
    private CtrlPersTeclats teclats;
    /**
     * Instància de CtrlDomini
     */
    private static CtrlDomini singletonObject;
    /**
     * Controlador de lectura de Fitxers
     */
    private CtrlFile ctrlFreqFile;


    /**
     * Creadora de CtrlDomini
     * <p> Inicialitza el controlador de domini i els controladors de persistencia</p>
     */
    public CtrlDomini() {
        inicialitzar();
    }

    /**
     * Retorna la instancia de CtrlDomini, si no existeix cap instancia de CtrlDomini es crea.
     * @return La instancia de CtrlDomini
     */
    public static CtrlDomini getInstance() { //CtrlDomini es singleton
        if(singletonObject == null){
            singletonObject = new CtrlDomini();
        }
        return singletonObject;
    }

    /**
     * Inicialitza el controlador de domini i els controladors de persistencia
     */
    public void inicialitzar(){
        ctrlFreqFile = CtrlFile.getInstance();
        perfils = CtrlPersPerfil.getInstance(this);
        llistes = CtrlPersFreq.getInstance(this);
        alfabets = CtrlPersAlfabets.getInstance(this);
        idiomes = CtrlPersIdiomes.getInstance(this);
        teclats = CtrlPersTeclats.getInstance(this);
        Estrategia = "BranchAndBound"; //estrategia per defecte
    }

    public void carregaPerfils() {
        perfils.carregar();
    }


    /**
     * Carrega les dades guardades dels alfabets i idiomes a memòria
     * @throws Exception Si no es poden carregar les dades
     */
    public void carregarDadesSistema() throws Exception{
        alfabets.carregar();
        idiomes.carregar();
    }


    /**
     * Carrega les dades guardades del perfil: llistes de frequencies i teclats a memòria
     * @throws Exception Si no es poden carregar les dades
     */
    public void carregarDadesPerfil() throws Exception{
        llistes.carregar();
        teclats.carregar();
    }

    /**
     * Guarda les dades del perfil: llistes de frequencies i teclats; i les dades dels alfabets i idiomes
     */
    public void guardaEstat() {
        llistes.guardar();
        perfils.guardar();
        teclats.guardar();
        alfabets.guardar();
        idiomes.guardar();
    }

    /**
     * Inicia una instància amb el perfil x
     * @param nom El nom del perfil
     * @throws Exception Si no es pot iniciar sessió amb el perfil
     */
    public void iniciaInstancia(String nom) throws Exception{
        PerfilActual = perfils.canviaPerfil(nom);
        llistes.canviaPerfil(nom);
        teclats.canviaPerfil(nom);
    }

    /**
     * Retorna el conjunt de noms d'usuari dels perfils
     * @return El conjunt de noms d'usuari dels perfils
     */
    public List<String> getAllPerfils() {
        return perfils.getAllPerfils();
    }

    /**
     * Retorna l'usuari del Perfil Actual.
     * @return L'usuari' del Perfil Actual
     */
    public String getPerfilActual() {
        return PerfilActual.getUsuari();
    }

    /**
     * Retorna el nom de l'estrategia per fer la distribució de teclat
     * @return El nom de l'estrategia per fer la distribució de teclat
     */
    public String getEstrategiaActual() {
        return Estrategia;
    }

    /**
     * Retorna true si l'String' un numero
     * @param paraula L'String a comprovar
     * @return True si l'String es un numero
     */
    private static boolean esNumero(String paraula) {
        try {
            Double.parseDouble(paraula);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Retorna el nom de l'idioma del teclat identificat per nomt
     * @param nomt El nom del teclat
     * @return El nom de l'idioma del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getNomIdiomaTeclat(String nomt) throws TeclatNoExisteix{
        return PerfilActual.getIdiomaTeclat(nomt);
    }

    /**
     * Retorna el nom de la llista de frequencies del teclat identificat per nomt
     * @param nomt El nom del teclat
     * @return El nom de la llista de frequencies del teclat identificat per nomt
     * @throws TeclatNoExisteix Si el teclat no existeix
     */
    public String getNomLListaTeclat(String nomt) throws TeclatNoExisteix{
        return PerfilActual.getLlistaTeclat(nomt);
    }

    /**
     * Passa les dades llegides d'un arxiu de llista de frequencies a un Map de paraules i frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @param LlistaLlegida Les dades de la llista de frequencies llegida
     * @return El Map de paraules i frequencies
     * @throws FormatNoValid Si el format de les dades llegides no es una llista o no es vàlid
     */
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

    /**
     * Passa les dades llegides d'un arxiu de text a un Map de paraules i frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @param LlistaLlegida Les dades de la llista de frequencies llegida
     * @return El Map de paraules i frequencies
     */
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

    /**
     * Llegeix l'informació de l'arxiu filepath i ho passa a llista de frequencies
     * @param tipusArxiu El tipus d'arxiu
     * @param filepath El nom de l'arxiu
     * @return El Map de paraules i frequencies llegit
     * @throws Exception Si no es pot llegir l'arxiu
     */
    public Map<String,Integer> llegirLlistaFreq(String tipusArxiu, String filepath) throws Exception {
        System.out.println("Llegint arxiu "+ filepath);
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiu(filepath);
        Map<String, Integer> novesEntrades = new HashMap<>();


        if (tipusArxiu == "llista") {
            novesEntrades = llistaToEntrades(novesEntrades,LlistaLlegida);
        }
        else {
            novesEntrades = textToEntrades(novesEntrades,LlistaLlegida);
        }
        return novesEntrades;
    }

    /**
     * Afegeix la informació de l'arxiu de llista de frequencies filepath al Perfil Actual com una nova llista de frequencies
     * @param tipusArxiu El tipus d'arxiu
     * @param filepath El path de l'arxiu
     * @param i El nom de l'idioma
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir la llista de frequencies
     */
    public void novaLlistaPerfil(String tipusArxiu, String filepath, String i , Map<String,Integer> novesEntrades) throws Exception {
        if (tipusArxiu != "Manual" && tipusArxiu != "Carregada") novesEntrades = llegirLlistaFreq(tipusArxiu,filepath);
        Idioma idiomaLlista = idiomes.getIdioma(i);
        LlistaFrequencies llista = PerfilActual.afegirLlistaFreq(filepath,idiomaLlista,novesEntrades);
        llistes.guardarLlistaFreq(llista);
    }

    /**
     * Modifica l'informació de la llista de frequencies identificada per nomLlista del Perfil Actual amb les noves dades entrades
     * @param tipusArxiu El tipus d'arxiu
     * @param filepath El nom de l'arxiu
     * @param nomLlista El nom de la llista de frequencies
     * @param novesEntrades El Map de paraules i frequencies
     * @throws Exception Si no es pot llegir l'arxiu o no es pot modificar la llista de frequencies
     */
    public void modificarLlistaPerfil(String tipusArxiu, String filepath, String nomLlista, Map<String,Integer> novesEntrades) throws Exception {
        if (tipusArxiu != "Manual") novesEntrades = llegirLlistaFreq(tipusArxiu,filepath);
        PerfilActual.modificarLlista(nomLlista, novesEntrades);
    }

    /**
     * Retorna els noms dels teclats del Perfil Actual
     * @return Els noms dels teclats del Perfil Actual
     */
    public List<String> getNomsTeclats() { return PerfilActual.getNomsTeclats();}

    /**
     * Retorna els noms de les llistes de frequencies del Perfil Actual
     * @return Els noms de les llistes de frequencies del Perfil Actual
     */
    public List<String> getNomLlistesGuardades() {
        return PerfilActual.getNomAllLlistes();
    }

    /**
     * Retorna el nom de l'idioma de la llista amb nom nomllista
     * @param nomllista El nom de la llista de frequencies
     * @return El nom de l'idioma de la llista amb nom nomllista
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public String getNomIdiomaLlista(String nomllista) throws LlistaFreqNoExisteix{
        return PerfilActual.getNomIdiomaLlista(nomllista);
    }

    /**
     * Retorna el Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @param nomSeleccio El nom de la llista de frequencies
     * @return El Map de paraules i frequencies de la llista amb nom nomSeleccio
     * @throws LlistaFreqNoExisteix Si la llista amb nom nomLlista no existeix
     */
    public Map<String, Integer> consultaLlista(String nomSeleccio) throws LlistaFreqNoExisteix {
        return PerfilActual.consultaLlista(nomSeleccio);
    }

    /**
     * Elimina la llista de frequencies amb nom nomLlista del Perfil Actual
     * @param nomLlista El nom de la llista de frequencies
     * @throws ExcepcionsCreadorTeclat Si la llista amb nom nomLlista no existeix o no es pot eliminar
     */
    public void eliminarLlista(String nomLlista) throws ExcepcionsCreadorTeclat{
        teclats.comprovarUsLlista(nomLlista);
        PerfilActual.eliminaLlista(nomLlista);
        llistes.eliminarLlista(nomLlista);
    }

    /**
     * Elimina el teclat amb nom nomTeclat del Perfil Actual
     * @param nomTeclat El nom del teclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot eliminar
     */
    public void eliminarTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat{
        PerfilActual.eliminarTeclat(nomTeclat);
        teclats.eliminarTeclat(nomTeclat);
    }

    /**
     * Afegeix l'alfabet de l'arxiu filepath al sistema
     * @param filepath El nom de l'arxiu
     * @throws Exception Si no es pot llegir l'arxiu o no es pot afegir l'alfabet
     */
    public void afegirAlfabet(String filepath) throws Exception {
        System.out.println("Llegint arxiu "+ filepath +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiu(filepath);
        alfabets.afegirAlfabet(filepath, LlistaLlegida);
    }

    /**
     * Elimina l'alfabet amb nom nomAlfabet del sistema
     * @param nomAlfabet El nom de l'alfabet
     * @throws ExcepcionsCreadorTeclat Si l'alfabet amb nom nomAlfabet no existeix o no es pot eliminar
     */
    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        alfabets.eliminarAlfabet(nomAlfabet);
    }

    /**
     * Retorna l'alfabet amb nom nom
     * @param nom El nom de l'alfabet
     * @return L'alfabet amb nom nom
     * @throws ExcepcionsCreadorTeclat Si l'alfabet amb nom nom no existeix
     */
    public Alfabet getAlfabet(String nom) throws ExcepcionsCreadorTeclat {
        return alfabets.getAlfabet(nom);
    }

    /**
     * Afegeix l'idioma amb nom nomIdioma, alfabet amb nom nomAlfabet i llista de frequencies creada amb les dades entrades
     * @param nomIdioma El nom de l'idioma
     * @param nomAlfabet El nom de l'alfabet
     * @param tipusArxiu El tipus d'arxiu
     * @param filepath El path de l'arxiu
     * @throws Exception Si l'idioma ja existeix o no es pot afegir l'idioma o la llista de frequencies
     */
    public void afegirIdioma(String nomIdioma, String nomAlfabet, String tipusArxiu, String filepath) throws Exception {
        Alfabet alfabetIdioma = alfabets.getAlfabet(nomAlfabet);
        Map<String, Integer> novesEntrades = llegirLlistaFreq(tipusArxiu, filepath);
        idiomes.afegirIdioma(nomIdioma, alfabetIdioma, novesEntrades);
    }

    /**
     * Elimina l'idioma amb nom nomIdioma del sistema
     * @param nomIdioma El nom de l'idioma
     * @throws ExcepcionsCreadorTeclat Si l'idioma amb nom nomIdioma no existeix o no es pot eliminar
     */
    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        if (!idiomes.existeix(nomIdioma)) throw new IdiomaNoExisteix();
        llistes.comprovarUsIdioma(nomIdioma);
        teclats.comprovarUsIdioma(nomIdioma);
        idiomes.eliminarIdioma(nomIdioma);
    }

    /**
     * Retorna la info dels idiomes del sistema
     * @return La info dels idiomes del sistema
     */
    public Vector<String> consultaIdiomes() {
        Vector<String> sdades = new Vector<String>();
        TreeMap<String, Idioma> Idiomes = idiomes.getIdiomes();

        int i = 1;
        for (Map.Entry<String, Idioma> idioma : Idiomes.entrySet()) {
            Idioma id = idioma.getValue();
            sdades.add(i + ". " + id.getInfo());
            ++i;
        }

        return sdades;
    }

    /**
     * Afegeix un teclat amb nom nomTeclat, idioma amb nom nomIdioma, llista de frequencies amb nom nomLlistaFreq, n files, m columnes i disposicio de lletres disposicio
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param nomLlistaFreq El nom de la llista de frequencies
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @param disposicio La disposició de lletres
     * @return El teclat creat
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public Teclat afegirTeclat(String nomTeclat, String nomIdioma, String nomLlistaFreq, int n, int m, char[][] disposicio) throws ExcepcionsCreadorTeclat{
        Idioma idiomaTeclat = idiomes.getIdioma(nomIdioma);
        LlistaFrequencies llista = llistes.getLlistaFreq(nomLlistaFreq);
        Teclat nouTeclat = PerfilActual.afegirTeclat(nomTeclat, llista, idiomaTeclat, n, m, disposicio);
        return nouTeclat;
    }

    /**
     * Afegeix i crea un teclat amb nom nomTeclat, idioma amb nom nomIdioma, llista de frequencies amb nom nomLlistaFreq, n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param nomLlistaFreq El nom de la llista de frequencies
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public void crearTeclatLlistaPropia(String nomTeclat, String nomIdioma, String nomLlistaFreq, int n, int m, String e) throws ExcepcionsCreadorTeclat{
        Idioma idiomaTeclat = idiomes.getIdioma(nomIdioma);
        Teclat nouTeclat = PerfilActual.crearTeclatLlistaPropia(nomTeclat, nomLlistaFreq, idiomaTeclat, n, m, e);
        teclats.afegirTeclat(nouTeclat);

    }

    /**
     * Afegeix i crea un teclat amb nom nomTeclat, idioma amb nom nomIdioma, la llista de l'idioma, n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param nomIdioma El nom de l'idioma
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat ja existeix o no es pot afegir el teclat
     */
    public void crearTeclatLlistaIdioma(String nomTeclat, String nomIdioma, int n, int m, String e) throws ExcepcionsCreadorTeclat{
        Idioma idiomaTeclat = idiomes.getIdioma(nomIdioma);
        Teclat nouTeclat = PerfilActual.crearTeclatLlistaIdioma(nomTeclat, idiomaTeclat, n, m, e);
        teclats.afegirTeclat(nouTeclat);
    }

    /**
     * Retorna la disposició de lletres del teclat amb nom nomTeclat
     * @param nomTeclat El nom del teclat
     * @return La disposició de lletres del teclat amb nom nomTeclat
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix
     */
    public char[][] consultaTeclat(String nomTeclat) throws ExcepcionsCreadorTeclat {
        return PerfilActual.consultaTeclat(nomTeclat);
    }

    /**
     * Modifica el layout del teclat identificat per nomTeclat amb n files i m columnes
     * @param nomTeclat El nom del teclat
     * @param n El nombre de files
     * @param m El nombre de columnes
     * @throws ExcepcionsCreadorTeclat Si el teclat amb nom nomTeclat no existeix o no es pot modificar el teclat
     */
    public void modificarLayoutTeclat(String nomTeclat, int n, int m) throws ExcepcionsCreadorTeclat {
        PerfilActual.modificarLayoutTeclat(nomTeclat, n, m);
    }

    /**
     * Consulta la info dels alfabets guardats al sistema
     * @return La info dels alfabets guardats al sistema
     */
    public Vector<String> consultaAlfabets() {
        Vector<String> sdades = new Vector<String>();
        TreeMap<String, Alfabet> Alfabets = alfabets.getAlfabets();

        int i = 1;
        for (Map.Entry<String, Alfabet> alfabet : Alfabets.entrySet()) {
            Alfabet a = alfabet.getValue();
            sdades.add(i +". " + a.getInfo());
            ++i;
        }

        return sdades;
    }


    public List<String> getNomsIdiomes() {
        Set<String> noms = idiomes.getIdiomes().keySet();
        return new ArrayList<>(noms);
    }

    public String consultaIdioma(String nomIdioma) throws IdiomaNoExisteix {
        return idiomes.getIdioma(nomIdioma).getInfo();
    }

    public String consultaAlfabet(String nomAlfabet) throws AlfabetNoExisteix {
        return alfabets.getAlfabet(nomAlfabet).getInfo();
    }

    public List<String> getNomsAlfabets()  {
        Set<String> noms = alfabets.getAlfabets().keySet();
        return new ArrayList<>(noms);
    }


}