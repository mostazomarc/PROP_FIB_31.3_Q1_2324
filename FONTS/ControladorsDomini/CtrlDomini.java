package ControladorsDomini;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Domini.*;
import Dades.*;

public class CtrlDomini {
    private Perfil PerfilActual; //Perfil que esta usant actualment el programa
    private String Estrategia; //Estrategia utilitzada en la fabricació del teclat
    private HashMap <String, Perfil> PerfilsActius; //Conjunt d'usuaris registrats
    private static CtrlDomini singletonObject;
    private CtrlFreqFile ctrlFreqFile;

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
        ctrlFreqFile = CtrlFreqFile.getInstance();
        Estrategia = "QAP"; //estrategia per defecte
        PerfilsActius = new HashMap<String, Perfil>();
        Alfabets = new TreeMap<String, Alfabet>();
        Idiomes = new TreeMap<String, Idioma>();
    }

    //Pre: Es rep un nom d'usuari
    //Post: Es crea l'usuari amb el nom rebut
    public void iniciaInstancia(String nom) {
        System.out.println("inicia sessio: " + nom +"\n");
        PerfilActual = new Perfil(nom);
        PerfilsActius.put(nom,PerfilActual);
    }


    //Pre:
    //Post: Retorna el conjunt de perfils.
    public HashMap<String, Perfil> getAllPerfils() {
        return PerfilsActius;
    }

    //Pre:
    //Post: Retorna la instancia del Perfil.
    public Perfil getPerfil(String id) {
        if (PerfilsActius.containsKey(id)) return PerfilsActius.get(id);
        else return null; //perfil no existeix
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


    //Pre: tipus arxiu es un tipus vàlid i filename existeix i esta en un format vàid
    //Post: S'afegeix la informació de l'arxiu de llista de frequencies filename al Perfil Actual
    public void llegirLlistaFreq(String tipusArxiu, String filename, String i) {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiuFreq(filename);
        Map<String, Integer> novesEntrades = new HashMap<>();


        if (tipusArxiu == "llista") {
            for (String linia : LlistaLlegida) {
                String[] parella = linia.split(" ");
                String paraula = parella[0];
                Integer frequencia = Integer.parseInt(parella[1]);

                if (novesEntrades.containsKey(paraula)) {
                    // Si la paraula ja existeix obtenir frequencia actual
                    int FrecVella = novesEntrades.get(paraula);
                    // Sumar la nova vella frequencia a la nova
                    frequencia += FrecVella;
                }

                novesEntrades.put(paraula,frequencia);
            }
        } else {
            Pattern patron = Pattern.compile("\\p{L}+");

            for (String linea : LlistaLlegida) {
                Matcher matcher = patron.matcher(linea);

                while (matcher.find()) {
                    String paraula = matcher.group();
                    int freq = 1;
                    if (novesEntrades.containsKey(paraula)) {
                        // Si la paraula ja existeix obtenir frequencia actual
                        freq += novesEntrades.get(paraula);
                    }

                    novesEntrades.put(paraula,freq);
                }
            }
        }

        PerfilActual.afegirLlistaFreq(filename,Idiomes.get(i),novesEntrades);

    }

    //Pre:
    //Post: S'obté un set dels noms de les llistes guardades del perfil actual
    public List<String> getNomLlistesGuardades() {
        return PerfilActual.getNomAllLlistes();
    }

    //Pre:
    //Post: S'obte la Llista de paraules i les seves frequencies amb nom nomSeleccio
    public Map<String, Integer> consultaLlista(String nomSeleccio) {
        return PerfilActual.consultaLlista(nomSeleccio);
    }

    public void afegirAlfabet(String filename) {
        System.out.println("Llegint arxiu "+ filename +"\n");
        List<String> LlistaLlegida = ctrlFreqFile.llegirArxiuFreq(filename);

        Set<Character> lletres = new HashSet<Character>();
        String nomAlfabet = filename.substring(0, filename.length() - 4);

        for (String linia : LlistaLlegida) {
            for (char lletra : linia.toCharArray()) {
                lletres.add(lletra);
            }
        }

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, lletres);
        Alfabets.put(nomAlfabet, nouAlfabet);
    }

    public void afegirIdioma(String nomIdioma, String nomAlfabet) {
        Alfabet alfabetIdioma = Alfabets.get(nomAlfabet);
        Idioma nouIdioma = new Idioma(nomIdioma, alfabetIdioma);
        Idiomes.put(nomIdioma, nouIdioma);
    }

    public Vector<String> consultaIdiomes() {
        Vector<String> sdades = new Vector<String>();
        Set<String> setkeys = Idiomes.keySet();
        Iterator<String> iterkeys = setkeys.iterator();
        while (iterkeys.hasNext()) {
            String nomIdioma = iterkeys.next();
            Idioma i = Idiomes.get(nomIdioma);
            Alfabet a = i.getAlfabet();
            String s = "";
            s += nomIdioma; s += " ";
            s += a.getNomAlfabet(); s += " ";
            s += a.getNumLletres();
            sdades.add(s);
        }
        return sdades;
    }


}