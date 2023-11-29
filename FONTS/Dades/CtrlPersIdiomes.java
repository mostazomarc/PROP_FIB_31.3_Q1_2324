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

/**
 * CtrlPersIdiomes és una classe que permet guardar i carregar idiomes
 * <p> Aquesta classe segueix el patró singleton</p>
 * <p> Aquesta classe és necessària per a la persistència d'idiomes</p>
 * @author Arnau Tajahuerce Brulles (arnau.tajahuerce@estudiantat.upc.edu)
 */
public class CtrlPersIdiomes {
    /**
     * Instància de CtrlPersIdiomes
     */
    private static CtrlPersIdiomes singletonObject;

    /**
     * Mapa dels idiomes del sistema
     */
    private TreeMap<String, Idioma> Idiomes;

    /**
     * Instància de CtrlDomini
     */
    private CtrlDomini controlador;

    /**
     * Retorna la instància de CtrlPersIdiomes, si no existeix cap CtrlPersIdiomes es crea.
     * @param c El controlador de domini
     * @return La instància de CtrlPersIdiomes
     */
    public static CtrlPersIdiomes getInstance(CtrlDomini c) {
        if(singletonObject == null)
            singletonObject = new CtrlPersIdiomes(c){

            };
        return singletonObject;
    }

    /**
     * Creadora de CtrlPersIdiomes
     * <p> Crea un conjunt d'idiomes i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersIdiomes(CtrlDomini c) {
        Idiomes = new TreeMap<String, Idioma>();
        controlador = c;
    }

    public void carregarIdiomes() throws Exception {
        controlador.afegirIdioma("Català","alfabetCatala","llista","catalaFreq.txt");
        controlador.afegirIdioma("Español","alfabetEspañol","llista","españolFreq.txt");
    }

    /**
     * Guarda els idiomes del sistema
     */
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

    /**
     * Afegeix un idioma
     * @param nomIdioma El nom de l'idioma a afegir
     * @param a L'alfabet de l'idioma a afegir
     * @param novesEntrades Llista de freqüències predeterminada de l'idioma a afegir
     * @throws ExcepcionsCreadorTeclat Si l'idioma ja existeix o hi ha algun problema a l'hora de crear la llista de freqüències
     */
    public void afegirIdioma(String nomIdioma, Alfabet a, Map<String, Integer> novesEntrades) throws ExcepcionsCreadorTeclat {
        if (existeix(nomIdioma)) throw new IdiomaJaExisteix(nomIdioma);
        Idioma nouIdioma = new Idioma(nomIdioma, a, novesEntrades);
        Idiomes.put(nomIdioma, nouIdioma);
    }

    /**
     * Elimina un idioma identificat per nomIdioma
     * @param nomIdioma El nom de l'idioma a eliminar
     * @throws IdiomaNoExisteix Si l'idioma no existeix
     */
    public void eliminarIdioma(String nomIdioma) throws IdiomaNoExisteix {
        Idioma i = getIdioma(nomIdioma);
        Alfabet a = i.getAlfabet();
        a.treureIdioma(nomIdioma);
        Idiomes.remove(nomIdioma);
    }

    /**
     * Obté si l'idioma identificat per nomIdioma existeix
     * <p> Retorna TRUE si l'idioma identificat per nomIdioma existeix en el sistema, FALSE en cas contrari</p>
     * @param nomIdioma El nom de l'idioma del qual es vol saber la seva existència
     * @return TRUE si l'idioma existeix, FALSE en cas contrari
     */
    public boolean existeix(String nomIdioma) {
        if (Idiomes.containsKey(nomIdioma)) return true;
        return false;
    }

    /**
     * Obté l'idioma identificat per nomIdioma
     * @param nomIdioma El nom de l'idioma
     * @return L'idioma identificat per nomIdioma
     * @throws IdiomaNoExisteix Si l'idioma no existeix
     */
    public Idioma getIdioma(String nomIdioma) throws IdiomaNoExisteix {
        if (!existeix(nomIdioma)) throw new IdiomaNoExisteix(nomIdioma);
        return Idiomes.get(nomIdioma);
    }

    /**
     * Obté tots els idiomes del sistema
     * @return El conjunt d'idiomes del sistema
     */
    public TreeMap<String, Idioma> getIdiomes() {
        return Idiomes;
    }
}
