package Dades;

import ControladorsDomini.CtrlDomini;
import Domini.Perfil;
import Excepcions.ExcepcionsCreadorTeclat;
import Excepcions.PerfilJaExisteix;
import Excepcions.PerfilNoExisteix;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * CtrlPersPerfil es una classe que permet guardar i carregar perfils
 * <p> Aquesta classe segueix el patró Singleton</p>
 * <p> Aquesta classe es necessària per a la persistència dels perfils</p>
 * @author Marc Mostazo González (marc.mostazo@estudiantat.upc.edu)
 */
public class CtrlPersPerfil {
    /**
     * Instància de CtrlPersPerfil
     */
    private static CtrlPersPerfil singletonObject;
    /**
     * Controlador de domini
     */
    private final CtrlDomini controlador;

    /**
     * Perfil actual
     */
    private Perfil perfilActual;

    Map<String, Perfil> perfils = new HashMap<>();

    /**
     * Creadora de CtrlPersPerfil
     * <p> Crea un conjunt de perfils i guarda el controlador</p>
     * @param c El controlador de domini
     */
    private CtrlPersPerfil(CtrlDomini c) {
        controlador = c;
    }

    /**
     * Retorna la instancia de CtrlPersPerfil, si no existeix cap CtrlPersPerfil es crea.
     * @param c El controlador de domini
     * @return La instancia de CtrlPersPerfil
     */
    public static CtrlPersPerfil getInstance(CtrlDomini c) {
        if (singletonObject == null)
            singletonObject = new CtrlPersPerfil(c) {
            };
        return singletonObject;
    }

    /**
     * Comprova si existeix un perfil amb nomPerfil al .json de perfils
     * @param nomPerfil El nom del perfil
     * @return TRUE si existeix un perfil amb nomPerfil, FALSE en cas contrari
     */
    private boolean perfilExisteix(String nomPerfil) {
        return perfils.containsKey(nomPerfil);
    }

    /**
     * Guarda el perfil actual al .json de perfils
     */
    public void guardar() {
        System.out.println("Guardant perfils");
        JSONArray ConjuntPerfils = new JSONArray();

        for (Map.Entry<String, Perfil> entry : perfils.entrySet()) { //Per cada perfil
            JSONObject nouUsuari = new JSONObject();
            nouUsuari.put("nomUsuari", entry.getValue().getUsuari());
            nouUsuari.put("Contrasenya", entry.getValue().getContrasenya());
            ConjuntPerfils.add(nouUsuari);
        }
        try (FileWriter file = new FileWriter("./DATA/Saves/UsuarisActius.json")) {
            file.write(ConjuntPerfils.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Carrega el perfil identificat per nomPerfil del .json de perfils
     * @return El perfil identificat per nomPerfil
     * @throws PerfilNoExisteix Si el perfil no existeix
     */
    public void carregar() throws PerfilNoExisteix {
        System.out.println("Carregant perfils");
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        perfils = new HashMap<>();
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            for (int i = 0; i < ConjuntPerfils.size(); ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null){    //Si el nom d'usuari coincideix
                    String contrasenya = ((String)next.get("Contrasenya"));  //Obtenim la contrasenya de l'usuari iessim
                    Perfil nouPerfil = new Perfil(nomUsuari, contrasenya);
                    perfils.put(nomUsuari, nouPerfil);
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {

        }
    }

    /**
     * Crea/carrega i guarda un perfil
     * @param nomPerfil El nom del perfil
     * @return El perfil creat/carregat
     * @throws PerfilJaExisteix Si el perfil ja existeix
     */
    public Perfil canviaPerfil(String nomPerfil) throws PerfilJaExisteix {
        try {
            if (!perfils.containsKey(nomPerfil)) throw new PerfilNoExisteix(nomPerfil);
            perfilActual = perfils.get(nomPerfil);
        } catch (PerfilNoExisteix perfilNoExisteix) {
            System.out.println("Perfil Nou");
            afegirPerfil(nomPerfil);
        }
        return perfilActual;
    }


    /**
     * Crea i guarda un perfil
     * @param nomPerfil El nom del perfil
     * @throws PerfilJaExisteix Si el perfil ja existeix
     */
    private void afegirPerfil(String nomPerfil) throws PerfilJaExisteix {
        if (perfilExisteix(nomPerfil)) throw new PerfilJaExisteix(nomPerfil);
        perfilActual = new Perfil(nomPerfil);
        perfils.put(nomPerfil, perfilActual);
    }

    /**
     * Obté el perfil identificat per nomPerfil
     * @param nomPerfil El nom del perfil
     * @return El perfil identificat per nomPerfil
     * @throws PerfilNoExisteix Si el perfil no existeix
     */
    public Perfil getPerfil(String nomPerfil) throws PerfilNoExisteix{
        if (!perfils.containsKey(nomPerfil)) throw new PerfilNoExisteix(nomPerfil);
        return perfils.get(nomPerfil);
    }


    /**
     * Obté el conjunt de noms dels perfils
     * @return El conjunt de noms dels perfils
     */
    public List<String> getAllPerfils() {
        return new ArrayList<>(perfils.keySet());
    }
}
