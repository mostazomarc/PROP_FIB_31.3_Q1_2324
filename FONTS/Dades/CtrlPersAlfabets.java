package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Alfabet;
import Excepcions.AlfabetEnUs;
import Excepcions.AlfabetJaExisteix;
import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.CaracterInvalid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

import java.util.*;

public class CtrlPersAlfabets {
    private static CtrlPersAlfabets singletonObject;
    private TreeMap<String, Alfabet> Alfabets;
    private CtrlDomini controlador;

    //Pre:
    //Post: Retorna la instancia de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
    public static CtrlPersAlfabets getInstance(CtrlDomini c) {
        if(singletonObject == null)
            singletonObject = new CtrlPersAlfabets(c);
        return singletonObject;
    }
    private CtrlPersAlfabets(CtrlDomini c) {
        Alfabets = new TreeMap<String, Alfabet>();
        controlador = c;
    }

    public void carregarAlfabets() throws Exception {
        controlador.afegirAlfabet("LlatíGenèric.txt");
        controlador.afegirAlfabet("alfabetEspañol.txt");
        controlador.afegirAlfabet("alfabetCatala.txt");
    }

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

    /*
    public void carregar() {
        System.out.println("Carregant Alfabets");
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader("./DATA/Saves/AlfabetsSistema.json")) {
            Object obj = jsonParser.parse(fileReader);
            JSONObject cjtAlfabets = (JSONObject) obj;

            for (Object key : cjtAlfabets.keySet()) {
                String alfabetKey = (String) key;
                JSONObject jsonAlfabet = (JSONObject) cjtAlfabets.get(alfabetKey);
                String nomAlfabet = (String) jsonAlfabet.get("nom");
                Set<Character> lletres = new HashSet<>((JSONArray) jsonAlfabet.get("lletres"));
                Alfabet a = new Alfabet(nomAlfabet, lletres);
                Alfabets.put(nomAlfabet.toLowerCase(), a);
            }

        } catch (IOException e) {
        } catch (ParseException e) {
        }
    }

     */

    public void carregar() {
        System.out.println("Carregant Alfabets");
        try (FileReader fileReader = new FileReader("./DATA/Saves/AlfabetsSistema.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);

            for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) jsonObject.entrySet()) {
                String key = entry.getKey();
                JSONObject jsonAlfabet = (JSONObject) entry.getValue();

                String nomAlfabet = (String) jsonAlfabet.get("nom");
                JSONArray lletresArray = (JSONArray) jsonAlfabet.get("lletres");
                Set<Character> lletres = new HashSet<>();
                for (int i = 0; i < lletresArray.size(); ++i) lletres.add(((String) lletresArray.get(i)).charAt(0));
                Alfabet a = new Alfabet(nomAlfabet, lletres);
                Alfabets.put(nomAlfabet.toLowerCase(), a);
            }
        } catch (IOException |ParseException e) {
        }
    }

    //Pre:
    //Post: S'afegeix l'Alfabet identificat per filename.length() - 4
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

    //Retorna TRUE si c és una lletra sense accent, FALSE si és un número, símbol, lletra amb accent...
    private boolean lletraValida(char c) {
        char c1 = Character.toLowerCase(c);
        if (!Character.isLetter(c)) return false; // Mira que és una lletra
        String caracterNormalizado = Normalizer.normalize(String.valueOf(c1), Normalizer.Form.NFD);
        if (!caracterNormalizado.equals(String.valueOf(c1)) && c1 != 'ñ' && c1 != 'ç') return false; //Mira que no té accent
        return true;
    }

    //Pre:
    //Post: S'elimina l'alfabet identificat per nomAlfabet
    public void eliminarAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        Alfabet a = getAlfabet(nomAlfabet.toLowerCase());
        if (a.numIdiomes() != 0) throw new AlfabetEnUs(nomAlfabet);
        Alfabets.remove(nomAlfabet.toLowerCase());
    }

    //Post: Retorna TRUE si existeix un Alfabet amb nomAlfabet, FALSE en cas contrari
    public boolean existeix(String nomAlfabet) {
        if (Alfabets.containsKey(nomAlfabet.toLowerCase())) return true;
        return false;
    }

    //Post: Retorna l'Alfabet identificat per nomAlfabet
    public Alfabet getAlfabet(String nomAlfabet) throws ExcepcionsCreadorTeclat {
        if (!existeix(nomAlfabet.toLowerCase())) throw new AlfabetNoExisteix();
        return Alfabets.get(nomAlfabet.toLowerCase());
    }

    //Post: Retorna el conjunt d'Alfabets
    public TreeMap<String, Alfabet> getAlfabets() {
        return Alfabets;
    }

}

//Classe Programada per: Arnau Tajahuerce
