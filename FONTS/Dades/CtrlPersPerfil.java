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
     * Mapa de perfils guardats
     */
    //private final HashMap<String, Perfil> PerfilsActius;

    private Perfil perfilActual;

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

    private boolean perfilExisteix(String nomPerfil) {
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        boolean trobat = false;
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            for (int i = 0; i < ConjuntPerfils.size() && !trobat; ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if (nomUsuari != null && nomUsuari.equals(nomPerfil)) trobat = true;
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }
        return trobat;
    }

    /**
     * Carrega els perfils dels arxius on estàn guardats (de moment crea noves, funcionarà al tenir capa de persistencia)
     * @throws ExcepcionsCreadorTeclat Si no es pot crear el perfil
     */
    public void carregarPerfil() throws Exception {
        controlador.iniciaInstancia("Prova");
    }

    public void guardar() {
        System.out.println("Guardant perfil" + perfilActual.getUsuari());
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < ConjuntPerfils.size() && !trobat; ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && nomUsuari.equals(perfilActual.getUsuari())){    //Si el nom d'usuari coincideix
                    ConjuntPerfils.remove(next); //L'esborrem i despres l'afegirem
                    trobat = true;  //Deixem de recorrer el vector
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }

        JSONObject nouUsuari = new JSONObject();
        nouUsuari.put("nomUsuari", perfilActual.getUsuari());
        nouUsuari.put("Contrasenya", perfilActual.getContrasenya());
        ConjuntPerfils.add(nouUsuari);
        try (FileWriter file = new FileWriter("./DATA/Saves/UsuarisActius.json")) {
            file.write(ConjuntPerfils.toJSONString()); //Escribim el conjunt d'usuaris al fitxer
            file.flush();
        } catch (IOException e) {
        }
    }

    public Perfil carregar(String nomPerfil) throws PerfilNoExisteix {
        System.out.println("Carregant perfil" + nomPerfil);
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < ConjuntPerfils.size() && !trobat; ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && nomUsuari.equals(nomPerfil)){    //Si el nom d'usuari coincideix
                    String contrasenya = ((String)next.get("Contrasenya"));  //Obtenim la contrasenya de l'usuari iessim
                    perfilActual = new Perfil(nomUsuari, contrasenya);
                    trobat = true;  //Deixem de recorrer el vector
                }
            }
            if (!trobat) throw new PerfilNoExisteix(nomPerfil);
        } catch (IOException e){
            throw new PerfilNoExisteix(nomPerfil);
        }
        catch (ParseException e) {

        }
        return perfilActual;
    }

    public Perfil canviaPerfil(String nomPerfil) throws PerfilJaExisteix {
        if (perfilActual != null) guardar();
        try {
            carregar(nomPerfil);
        } catch (PerfilNoExisteix perfilNoExisteix) {
            afegirPerfil(nomPerfil);
        }
        return perfilActual;
    }


    /**
     * Crea i guarda un perfil
     * @param nomPerfil El nom del perfil
     * @return El perfil creat
     * @throws PerfilJaExisteix Si el perfil ja existeix
     */
    private void afegirPerfil(String nomPerfil) throws PerfilJaExisteix {
        if (perfilExisteix(nomPerfil)) throw new PerfilJaExisteix(nomPerfil);
        perfilActual = new Perfil(nomPerfil);
    }

    /**
     * Obté el perfil identificat per nomPerfil
     * @param nomPerfil El nom del perfil
     * @return El perfil identificat per nomPerfil
     * @throws PerfilNoExisteix Si el perfil no existeix
     */
    public Perfil getPerfil(String nomPerfil) throws PerfilNoExisteix{
        if (!perfilExisteix(nomPerfil)) {
            if (perfilActual.getUsuari().equals(nomPerfil)) return perfilActual;
            throw new PerfilNoExisteix(nomPerfil);
        }
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        Perfil perfilTrobat = null;
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            boolean trobat = false;
            for (int i = 0; i < ConjuntPerfils.size() && !trobat; ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                if(nomUsuari != null && nomUsuari.equals(nomPerfil)){    //Si el nom d'usuari coincideix
                    String contrasenya = ((String)next.get("Contrasenya"));  //Obtenim la contrasenya de l'usuari iessim
                    trobat = true;  //Deixem de recorrer el vector
                     perfilTrobat= new Perfil(nomUsuari, contrasenya);
                }
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }
        if (perfilTrobat == null) throw new PerfilNoExisteix(nomPerfil);
        return perfilTrobat;
    }


    /**
     * Obté el conjunt de noms dels perfils
     * @return El conjunt de noms dels perfils
     */
    public List<String> getAllPerfils() {
        List<String> nomsPerfils = new ArrayList<>();
        JSONParser jsP = new JSONParser();
        JSONArray ConjuntPerfils = new JSONArray();
        try (FileReader rd = new FileReader("./DATA/Saves/UsuarisActius.json")){
            ConjuntPerfils = (JSONArray) jsP.parse(rd);
            for (int i = 0; i < ConjuntPerfils.size(); ++i){
                JSONObject next = (JSONObject) ConjuntPerfils.get(i); //Obtenim l'objecte de l'usuari iessim
                String nomUsuari = ((String)next.get("nomUsuari"));  //Obtenim el nom d'usuari de l'usuari iessim
                nomsPerfils.add(nomUsuari);
            }
        } catch (IOException e){
        }
        catch (ParseException e) {
        }
        return nomsPerfils;
    }


}
