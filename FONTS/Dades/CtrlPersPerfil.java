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

    /**
     * Conjunt de perfils
     */
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
     * Comprova si existeix un perfil amb nomPerfil al conjunt de perfils carregat
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
            ConjuntPerfils.add(nouUsuari);
        }
        try (FileWriter file = new FileWriter("./DATA/Saves/UsuarisActius.json")) {
            file.write(ConjuntPerfils.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    /**
     * Carrega els perfils del .json de perfils
     */
    public void carregar() {
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
                    Perfil nouPerfil = new Perfil(nomUsuari);
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
    public Perfil canviaPerfil(String nomPerfil) throws PerfilNoExisteix {
        if (!perfils.containsKey(nomPerfil)) throw new PerfilNoExisteix(nomPerfil);
        perfilActual = perfils.get(nomPerfil);
        return perfilActual;
    }


    /**
     * Crea i guarda un perfil
     * @param nomPerfil El nom del perfil
     * @throws PerfilJaExisteix Si el perfil ja existeix
     */
    public void afegirPerfil(String nomPerfil) throws ExcepcionsCreadorTeclat {
        if (perfilExisteix(nomPerfil)) throw new PerfilJaExisteix(nomPerfil);
        perfilActual = new Perfil(nomPerfil);
        perfils.put(nomPerfil, perfilActual);
        guardar();
        canviaPerfil(nomPerfil);
    }

    /**
     * Elimina un perfil del conjunt de perfils
     * @param nom El nom del perfil
     * @throws PerfilNoExisteix Si el perfil no existeix
     */
    public void eliminaPerfil(String nom) throws PerfilNoExisteix{
        System.out.println("Eliminant perfil " + nom);
        if (!perfilExisteix(nom)) throw new PerfilNoExisteix(nom);
        if (perfilActual != null && perfilActual.getUsuari().equals(nom)) perfilActual = null;
        perfils.remove(nom);
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
     * Obté el perfil actual
     * @return El perfil actual null si no hi ha cap perfil actual
     */
    public Perfil getPerfilActual() throws PerfilNoExisteix{
        return perfilActual;
    }


    /**
     * Obté el conjunt de noms dels perfils
     * @return El conjunt de noms dels perfils
     */
    public List<String> getAllPerfils() {
        return new ArrayList<>(perfils.keySet());
    }
}
