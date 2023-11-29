package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Alfabet;
import Domini.Idioma;
import Excepcions.AlfabetNoExisteix;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.IdiomaJaExisteix;
import Excepcions.IdiomaNoExisteix;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CtrlPersIdiomes {
    private static CtrlPersIdiomes singletonObject;
    private TreeMap<String, Idioma> Idiomes;
    private CtrlDomini controlador;

    //Pre:
    //Post: Retorna la instancia de CtrlPersAlfabets, si no existeix cap CtrlPersAlfabets es crea.
    public static CtrlPersIdiomes getInstance(CtrlDomini c) {
        if(singletonObject == null)
            singletonObject = new CtrlPersIdiomes(c){

            };
        return singletonObject;
    }

    private CtrlPersIdiomes(CtrlDomini c) {
        Idiomes = new TreeMap<String, Idioma>();
        controlador = c;
    }

    public void carregarIdiomes() throws Exception {
        controlador.afegirIdioma("Català","alfabetCatala","llista","catalaFreq.txt");
        controlador.afegirIdioma("Español","alfabetEspañol","llista","españolFreq.txt");
    }

    public void guardar() {
        System.out.println("Guardant Idiomes");
        JSONObject CjtIdiomes = new JSONObject();

        for (Map.Entry<String, Idioma> entry : Idiomes.entrySet()) {
            Idioma i = entry.getValue();
            JSONObject jsonIdioma = new JSONObject();
            jsonIdioma.put("nom", i.getNom());
            jsonIdioma.put("nomAlfabet", i.getAlfabet().getNomAlfabet());

            Map<String, Integer> llistaParaules = i.getFrequencies();

            JSONArray paraules = new JSONArray();
            for (Map.Entry<String, Integer> paraula : llistaParaules.entrySet()) {
                JSONObject jsonParaula = new JSONObject();
                jsonParaula.put("paraula", paraula.getKey());
                jsonParaula.put("freq", paraula.getValue());
                paraules.add(jsonParaula);
            }

            jsonIdioma.put("llistaParaules", paraules);
            CjtIdiomes.put(entry.getKey(), jsonIdioma);
        }
        try (FileWriter fileWriter = new FileWriter("./DATA/Saves/IdiomesSistema.json")) {
            fileWriter.write(CjtIdiomes.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
        }
    }

    /*
    public void carregar() throws Exception {
        System.out.println("Carregant Idiomes");
        JSONParser jsonParser = new JSONParser();
        JSONArray CjtIdiomes = new JSONArray();
        try (FileReader fileReader = new FileReader("./DATA/Saves/LlistesUsuarisActius.json")) {
            CjtIdiomes = (JSONArray) jsonParser.parse(fileReader);
            for (int i = 0; i < CjtIdiomes.size(); ++i) {
                JSONObject jsonIdioma = (JSONObject) CjtIdiomes.get(i);
                String nomIdioma = (String) jsonIdioma.get("nom");
                String nomAlfabet = (String) jsonIdioma.get("nomAlfabet");
                Alfabet a = controlador.getAlfabet(nomAlfabet);
                JSONArray paraules = (JSONArray) jsonIdioma.get("llistaParaules");
                Map<String, Integer> llistaParaules = new HashMap<>();
                for (int k = 0; k < paraules.size(); ++k) {
                    JSONObject jsonParaula = (JSONObject) paraules.get(k);
                    String paraulaString = (String) jsonParaula.get("paraula");
                    Long freq = (Long) jsonParaula.get("freq");
                    llistaParaules.put(paraulaString, freq.intValue());
                }
                controlador.novaLlista()


            }
        } catch (IOException e) {
        } catch (ParseException e) {
        }
    }

     */

    //Pre:
    //Post: S'afegeix l'Idioma identificat per nomIdioma
    public void afegirIdioma(String nomIdioma, Alfabet a, Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        if (existeix(nomIdioma)) throw new IdiomaJaExisteix(nomIdioma);
        Idioma nouIdioma = new Idioma(nomIdioma, a, novesEntrades);
        Idiomes.put(nomIdioma, nouIdioma);
    }

    //Pre:
    //Post: S'elimina l'Idioma identificat per nomIdioma
    public void eliminarIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        Idioma i = getIdioma(nomIdioma);
        Alfabet a = i.getAlfabet();
        a.treureIdioma(nomIdioma);
        Idiomes.remove(nomIdioma);
    }

    //Pre:
    //Post: Retorna TRUE si existeix un Idioma amb nomIdioma, FALSE en cas contrari
    public boolean existeix(String nomIdioma) {
        if (Idiomes.containsKey(nomIdioma)) return true;
        return false;
    }

    //Pre:
    //Post: Retorna l'Idioma identificat per nomIdioma
    public Idioma getIdioma(String nomIdioma) throws ExcepcionsCreadorTeclat {
        if (!existeix(nomIdioma)) throw new IdiomaNoExisteix(nomIdioma);
        return Idiomes.get(nomIdioma);
    }

    //Pre:
    //Post: Retorna el conjunt d'Idiomes
    public TreeMap<String, Idioma> getIdiomes() {
        return Idiomes;
    }
}

//Classe Programada per: Arnau Tajahuerce
