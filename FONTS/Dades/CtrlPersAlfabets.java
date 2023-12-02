package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Alfabet;
import Excepcions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

import java.util.*;

/**
 * CtrlPersAlfabets és una classe que permet guardar i carregar alfabets
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència d'alfabets</p>
 * @author Arnau Tajahuerce Brulles (arnau.tajahuerce@estudiantat.upc.edu)
 */
public class CtrlPersAlfabets {
    /**
     * Instància de CtrlPersFreq
     */
    private static CtrlPersAlfabets singletonObject;

    /**
     * Mapa dels alfabets del sistema
     */
    private TreeMap<String, Alfabet> Alfabets;

    /**
     * Instància de CtrlDomini
     */
    private CtrlDomini controlador;

    /**
     * Retorna la instància de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
     * @param c El controlador de domini
     * @return La instància de CtrlPersAlfabets
     */
    public static CtrlPersAlfabets getInstance(CtrlDomini c) {
        if(singletonObject == null)
            singletonObject = new CtrlPersAlfabets(c);
        return singletonObject;
    }

    /**
     * Creadora de CtrlPersAlfabets
     * <p> Crea un conjunt d'idiomes i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersAlfabets(CtrlDomini c) {
        Alfabets = new TreeMap<String, Alfabet>();
        controlador = c;
    }

    /**
     * Guarda els alfabets del sistema
     */
    public void guardar() {
        System.out.println("Guardant Alfabets");
        JSONObject CjtAlfabets = new JSONObject();
        for (Map.Entry<String, Alfabet> entry : Alfabets.entrySet()) {
            Alfabet a = entry.getValue();
            JSONObject jsonAlfabet = new JSONObject();
            jsonAlfabet.put("nom", a.getNomAlfabet());
            JSONArray lletres = new JSONArray();
            for (char l : a.getLletres()) lletres.add(String.valueOf(l));
            jsonAlfabet.put("lletres", lletres);
            CjtAlfabets.put(entry.getKey(), jsonAlfabet);
        }
        try (FileWriter fileWriter = new FileWriter("./DATA/Saves/AlfabetsSistema.json")) {
            fileWriter.write(CjtAlfabets.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Carrega els alfabets del sistema
     */
    public void carregar() {
        System.out.println("Carregant Alfabets");
        try (FileReader fileReader = new FileReader("./DATA/Saves/AlfabetsSistema.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);

            for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) jsonObject.entrySet()) {
                JSONObject jsonAlfabet = (JSONObject) entry.getValue();

                String nomAlfabet = (String) jsonAlfabet.get("nom");
                JSONArray lletresArray = (JSONArray) jsonAlfabet.get("lletres");
                Set<Character> lletres = new HashSet<>();
                for (int i = 0; i < lletresArray.size(); ++i) lletres.add(((String) lletresArray.get(i)).charAt(0));

                Alfabet a = new Alfabet(nomAlfabet, lletres);
                Alfabets.put(nomAlfabet.toLowerCase(), a);
            }
        } catch (IOException | ParseException e) {
        }
    }

    /**
     * Afegeix un alfabet
     * @param filename El nom de l'arxiiu que conté les lletres de l'alfabet (Serà el nom de l'alfabet)
     * @param LlistaLlegida Les lletres de l'alfabet
     * @throws ExcepcionsCreadorTeclat Si l'alfabet ja existeix o l'arxiu conté algún caràcter invàlid
     */
    public void afegirAlfabet(String filename, List<String> LlistaLlegida) throws ExcepcionsCreadorTeclat {

        String nomAlfabet = filename.substring(0, filename.length() - 4);

        if (existeix(nomAlfabet)) throw new AlfabetJaExisteix(nomAlfabet);

        Set<Character> lletres = new HashSet<Character>();

        for (String linia : LlistaLlegida) {
            for (char lletra : linia.toCharArray()) {
                if (!lletraValida(lletra)) throw new CaracterInvalid(filename);
                lletres.add(lletra);
            }
        }

        Alfabet nouAlfabet = new Alfabet(nomAlfabet, lletres);
        Alfabets.put(nomAlfabet.toLowerCase(), nouAlfabet);
    }

    /**
     * Obté si el caràcter c és vàlid
     * @param c Caràcter del qual es vol saber si és vàlid
     * @return TRUE si c és una lletra sense accent, FALSE si és un número, símbol, lletra amb accent
     */
    private boolean lletraValida(char c) {
        char c1 = Character.toLowerCase(c);
        if (!Character.isLetter(c)) return false; // Mira que és una lletra
        String caracterNormalizado = Normalizer.normalize(String.valueOf(c1), Normalizer.Form.NFD);
        if (!caracterNormalizado.equals(String.valueOf(c1)) && c1 != 'ñ' && c1 != 'ç') return false; //Mira que no té accent
        return true;
    }

    /**
     * Elimina un alfabet identificat per nomAlfabet
     * @param nomAlfabet El nom de l'alfabet a eliminar
     * @throws ExcepcionsCreadorTeclat Si l'alfabet no existeix o és l'alfabet d'algún idioma del sistema
     */
    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        Alfabet a = getAlfabet(nomAlfabet.toLowerCase());
        if (a.numIdiomes() != 0) throw new AlfabetEnUs(nomAlfabet);
        Alfabets.remove(nomAlfabet.toLowerCase());
    }

    /**
     * Obté si l'alfabet identificat per nomAlfabet existeix
     * <p> Retorna TRUE si l'alfabet identificat per nomAlfabet existeix en el sistema, FALSE en cas contrari</p>
     * @param nomAlfabet El nom de l'alfabet del qual es vol saber la seva existència
     * @return TRUE si l'alfabet existeix, FALSE en cas contrari
     */
    public boolean existeix(String nomAlfabet) {
        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) return true;
        return false;
    }

    /**
     * Obté l'alfabet identificat per nomAlfabet
     * @param nomAlfabet El nom de l'alfabet
     * @return L'alfabet identificat per nomAlfabet
     * @throws AlfabetNoExisteix Si l'alfabet no existeix
     */
    public Alfabet getAlfabet(String nomAlfabet) throws AlfabetNoExisteix {
        if (!existeix(nomAlfabet.toLowerCase())) throw new AlfabetNoExisteix();
        return Alfabets.get(nomAlfabet.toLowerCase());
    }

    /**
     * Obté tots els alfabets del sistema
     * @return El conjunt d'alfabets del sistema
     */
    public TreeMap<String, Alfabet> getAlfabets() {
        return Alfabets;
    }

}
